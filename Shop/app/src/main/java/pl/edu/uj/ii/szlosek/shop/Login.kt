package pl.edu.uj.ii.szlosek.shop

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import io.realm.Realm
import pl.edu.uj.ii.szlosek.shop.builds.getLoginData
import pl.edu.uj.ii.szlosek.shop.builds.getProducts
import pl.edu.uj.ii.szlosek.shop.models.LoginData
import pl.edu.uj.ii.szlosek.shop.models.Product
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations
import androidx.core.app.ActivityCompat.startActivityForResult
import android.R.attr.data
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.*
import org.json.JSONObject
import org.json.JSONTokener
import java.io.OutputStreamWriter
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection
import java.io.File
import java.net.CookieManager
import java.util.Calendar.getInstance
import java.util.Currency.getInstance
import java.util.ResourceBundle.clearCache

class Login : AppCompatActivity() {

    lateinit var githubAuthURLFull: String
    lateinit var githubdialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
     //   File(this.cacheDir.path).deleteRecursively()
        RetrofitCreator().buildAllServices()

      //  val product = Product(9, "Bluza bez kaptura", 1, "M", 13, 29.99F, "Męska bluza z kapturem.")
      //  getProducts()
     //   getProduct(2)
     //   deleteProduct(6)
     //   println("XDDDD")
     //   var x = getProduct(2)
     //   println(x.description + " haha")

        Realm.init(this)
        RealmConfig.providesRealmConfig()
        runBlocking {
            withContext(Dispatchers.IO) {
              //  createProduct(product)

                val x = RealmOperations()
                x.synchronizeAllModels()

                val x2 = getProducts()
                println(x2)
            }
        }

        val state = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        githubAuthURLFull =
            GithubConstants.AUTHURL + "?client_id=" + GithubConstants.CLIENT_ID + "&scope=" + GithubConstants.SCOPE + "&redirect_uri=" + GithubConstants.REDIRECT_URI + "&state=" + state

        val github_login_btn = findViewById<Button>(R.id.github_login_btn)
        github_login_btn.setOnClickListener {
            setupGithubWebviewDialog(githubAuthURLFull)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setupGithubWebviewDialog(url: String) {
        githubdialog = Dialog(this)
        val webView = WebView(this)

        webView.clearHistory();
        webView.clearFormData();
        webView.clearCache(true);
        android.webkit.CookieManager.getInstance().removeAllCookie();

        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = GithubWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        githubdialog.setContentView(webView)
        githubdialog.show()
    }

    fun fromLoginToRegister(view: android.view.View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    fun fromLogToProducts(view: android.view.View) {
        val login = findViewById<EditText>(R.id.login) as EditText
        val password = findViewById<EditText>(R.id.password) as EditText
        var loginData = emptyList<LoginData>()

        runBlocking {
            withContext(Dispatchers.IO) {
                loginData = getLoginData()
            }
        }

        loginData.forEach {
            if (it.login == login.text.toString() && it.password == password.text.toString()) {
                val intent = Intent(this, Products::class.java)
                startActivity(intent)
            } else {
                println("NIE DLA PSA!!!")
            }
        }
    }

    @Suppress("OverridingDeprecatedMember")
    inner class GithubWebViewClient : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            if (request!!.url.toString().startsWith(GithubConstants.REDIRECT_URI)) {
                handleUrl(request.url.toString())

                // Close the dialog after getting the authorization code
                if (request.url.toString().contains("code=")) {
                    githubdialog.dismiss()
                }
                return true
            }
            return false
        }

        // For API 19 and below
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith(GithubConstants.REDIRECT_URI)) {
                handleUrl(url)

                // Close the dialog after getting the authorization code
                if (url.contains("?code=")) {
                    githubdialog.dismiss()
                }
                return true
            }
            return false
        }

        // Check webview url for access token code or error
        private fun handleUrl(url: String) {
            val uri = Uri.parse(url)
            if (url.contains("code")) {
                val githubCode = uri.getQueryParameter("code") ?: ""
                requestForAccessToken(githubCode)
            }
        }

        fun requestForAccessToken(code: String) {
            val grantType = "authorization_code"

            val postParams =
                "grant_type=" + grantType + "&code=" + code + "&redirect_uri=" + GithubConstants.REDIRECT_URI + "&client_id=" + GithubConstants.CLIENT_ID + "&client_secret=" + GithubConstants.CLIENT_SECRET
            GlobalScope.launch(Dispatchers.Default) {
                val url = URL(GithubConstants.TOKENURL)
                val httpsURLConnection =
                    withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }
                httpsURLConnection.requestMethod = "POST"
                httpsURLConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                );
                httpsURLConnection.doInput = true
                httpsURLConnection.doOutput = true
                val outputStreamWriter = OutputStreamWriter(httpsURLConnection.outputStream)
                withContext(Dispatchers.IO) {
                    outputStreamWriter.write(postParams)
                    outputStreamWriter.flush()
                }
                val response = httpsURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                withContext(Dispatchers.Main) {
                    val jsonObject = JSONTokener(response).nextValue() as JSONObject

                    val accessToken = jsonObject.getString("access_token") //The access token

                    // Get user's id, first name, last name, profile pic url
                    fetchGithubUserProfile(accessToken)
                }
            }
        }

        fun fetchGithubUserProfile(token: String) {
            GlobalScope.launch(Dispatchers.Default) {
                val tokenURLFull =
                    "https://api.github.com/user"

                val url = URL(tokenURLFull)
                val httpsURLConnection =
                    withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.setRequestProperty("Authorization", "Bearer $token")
                httpsURLConnection.doInput = true
                httpsURLConnection.doOutput = false
                val response = httpsURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                Log.i("GitHub Access Token: ", token)

                // GitHub Id
                val githubId = jsonObject.getInt("id")
                Log.i("GitHub Id: ", githubId.toString())

                // GitHub Display Name
                val githubDisplayName = jsonObject.getString("login")
                Log.i("GitHub Display Name: ", githubDisplayName)

                // GitHub Email
                val githubEmail = jsonObject.getString("email")
                Log.i("GitHub Email: ", githubEmail)

                // GitHub Profile Avatar URL
                val githubAvatarURL = jsonObject.getString("avatar_url")
                Log.i("Github Profile Avatar URL: ", githubAvatarURL)
                // https://johncodeos.com/how-to-add-github-login-button-to-your-android-app-using-kotlin/
            }

            goToProducts()
        }
    }

    fun goToProducts() {
        val intent = Intent(this, Products::class.java)
        startActivity(intent)
    }

}
