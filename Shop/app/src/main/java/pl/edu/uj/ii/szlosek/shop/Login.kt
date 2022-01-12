package pl.edu.uj.ii.szlosek.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    //    RetrofitCreator().buildAllServices()

      //  val product = Product(6, "Bluza bez kaptura", 1, "M", 13, 29.99F, "Męska bluza z kapturem.")
       // addProduct(product)
      //  getProducts()
     //   getProduct(2)
     //   deleteProduct(6)
     //   println("XDDDD")
     //   var x = getProduct(2)
     //   println(x.description + " haha")

/*
        Realm.init(this)
        RealmConfig.providesRealmConfig()
        runBlocking {
            withContext(Dispatchers.IO) {
                var x = RealmOperations()
                x.synchronizeAllModels()
            }
        } */
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