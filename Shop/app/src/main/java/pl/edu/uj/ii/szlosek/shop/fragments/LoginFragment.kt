package pl.edu.uj.ii.szlosek.shop.fragments

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.coroutines.*
import org.json.JSONObject
import org.json.JSONTokener
import pl.edu.uj.ii.szlosek.shop.*
import pl.edu.uj.ii.szlosek.shop.builds.getLoginData
import pl.edu.uj.ii.szlosek.shop.models.LoginData
import java.io.OutputStreamWriter
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection

class LoginFragment : Fragment(R.layout.fragment_login) {
    lateinit var githubAuthURLFull: String
    lateinit var githubdialog: Dialog

    override fun onStart() {
        super.onStart()

        val buttonLogin = (view?.findViewById(R.id.buttonLogin)) as Button
        val buttonRegister = (view?.findViewById(R.id.buttonRegister)) as Button

        buttonLogin.setOnClickListener {
            val login = view?.findViewById<EditText>(R.id.login)
            val password = view?.findViewById<EditText>(R.id.password)
            var loginData = emptyList<LoginData>()

            runBlocking {
                withContext(Dispatchers.IO) {
                    loginData = getLoginData()
                }
            }

            loginData.forEach {
                if (it.login == login?.text.toString() && it.password == password?.text.toString()) {
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToProductsFragment()
                    view!!.findNavController().navigate(action)
                    UserId.id = it.id
                } else {
                   // Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonRegister.setOnClickListener {
            val action =
                LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment()
            view!!.findNavController().navigate(action)
        }

        val state = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        githubAuthURLFull =
            GithubConstants.AUTHURL + "?client_id=" + GithubConstants.CLIENT_ID + "&scope=" + GithubConstants.SCOPE + "&redirect_uri=" + GithubConstants.REDIRECT_URI + "&state=" + state

        val githubLoginBtn = view?.findViewById<Button>(R.id.github_login_btn)
        githubLoginBtn?.setOnClickListener {
            setupGithubWebviewDialog(githubAuthURLFull)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setupGithubWebviewDialog(url: String) {
        githubdialog = Dialog(context!!)
        val webView = WebView(context!!)

        webView.clearHistory();
        webView.clearFormData();
        webView.clearCache(true);
        android.webkit.CookieManager.getInstance().removeAllCookie()
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = GithubWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        githubdialog.setContentView(webView)
        githubdialog.show()
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

                if (request.url.toString().contains("code=")) {
                    githubdialog.dismiss()
                }
                return true
            }
            return false
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith(GithubConstants.REDIRECT_URI)) {
                handleUrl(url)

                if (url.contains("?code=")) {
                    githubdialog.dismiss()
                }
                return true
            }
            return false
        }

        private fun handleUrl(url: String) {
            val uri = Uri.parse(url)
            if (url.contains("code")) {
                val githubCode = uri.getQueryParameter("code") ?: ""
                requestForAccessToken(githubCode)
            }
        }

        private fun requestForAccessToken(code: String) {
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

                    fetchGithubUserProfile(accessToken)
                }
            }
        }

        private fun fetchGithubUserProfile(token: String) {
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

                val githubId = jsonObject.getInt("id")
                Log.i("GitHub Id: ", githubId.toString())

                val githubDisplayName = jsonObject.getString("login")
                Log.i("GitHub Display Name: ", githubDisplayName)

                val githubEmail = jsonObject.getString("email")
                Log.i("GitHub Email: ", githubEmail)

                val githubAvatarURL = jsonObject.getString("avatar_url")
                Log.i("Github Profile Avatar URL: ", githubAvatarURL)
                // https://johncodeos.com/how-to-add-github-login-button-to-your-android-app-using-kotlin/
            }
            val action =
                LoginFragmentDirections
                    .actionLoginFragmentToProductsFragment()
            view!!.findNavController().navigate(action)
        }
    }
}