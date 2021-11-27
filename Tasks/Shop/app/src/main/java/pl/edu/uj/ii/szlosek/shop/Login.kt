package pl.edu.uj.ii.szlosek.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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