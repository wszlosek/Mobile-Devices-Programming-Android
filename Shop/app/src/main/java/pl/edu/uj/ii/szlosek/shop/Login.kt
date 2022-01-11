package pl.edu.uj.ii.szlosek.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.OkHttpClient
import pl.edu.uj.ii.szlosek.shop.builds.*
import pl.edu.uj.ii.szlosek.shop.models.Product
import pl.edu.uj.ii.szlosek.shop.services.ProductService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.models.User.UserRealmFunc
import pl.edu.uj.ii.szlosek.shop.services.UserService

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        RetrofitCreator().buildAllServices()

      //  val product = Product(6, "Bluza bez kaptura", 1, "M", 13, 29.99F, "Męska bluza z kapturem.")
       // addProduct(product)
      //  getProducts()
     //   getProduct(2)
     //   deleteProduct(6)
     //   println("XDDDD")
     //   var x = getProduct(2)
     //   println(x.description + " haha")

        runBlocking {
            withContext(Dispatchers.IO) {
                var x = getCarts()
                println(x)
            }
        }

        /*
        Realm.init(this)
        RealmConfig.providesRealmConfig() */
    }

    fun fromLoginToRegister(view: android.view.View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    fun fromLogToProducts(view: android.view.View) {
        val intent = Intent(this, Products::class.java)
        startActivity(intent)
    }
}