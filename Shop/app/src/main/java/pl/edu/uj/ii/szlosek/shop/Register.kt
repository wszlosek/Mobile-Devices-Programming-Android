package pl.edu.uj.ii.szlosek.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun fromRegisterToLogin(view: android.view.View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}