package pl.edu.uj.ii.szlosek.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    fun fromAboutMeToProducts(view: android.view.View) {
        val intent = Intent(this, Products::class.java)
        startActivity(intent)
    }

    fun fromAboutToMaps(view: android.view.View) {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}