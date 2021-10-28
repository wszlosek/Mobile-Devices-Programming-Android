package pl.edu.uj.ii.szlosek.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: android.view.View) {
        var label = findViewById<TextView>(R.id.label)
        label.text = "Android course"
    }
}