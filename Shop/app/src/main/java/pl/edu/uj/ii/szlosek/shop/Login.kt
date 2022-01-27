package pl.edu.uj.ii.szlosek.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import pl.edu.uj.ii.szlosek.shop.builds.getLoginData
import pl.edu.uj.ii.szlosek.shop.builds.getProducts
import pl.edu.uj.ii.szlosek.shop.models.LoginData
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.stripe.android.PaymentConfiguration
import kotlinx.coroutines.*
import org.json.JSONObject
import org.json.JSONTokener
import java.io.OutputStreamWriter
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection

class Login : AppCompatActivity() {

    lateinit var githubAuthURLFull: String
    lateinit var githubdialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        PaymentConfiguration.init(
            applicationContext,
            Secrets.secretKeyClient
        )

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
    }

    fun goToProducts() {
        val intent = Intent(this, Products::class.java)
        startActivity(intent)
    }
}
