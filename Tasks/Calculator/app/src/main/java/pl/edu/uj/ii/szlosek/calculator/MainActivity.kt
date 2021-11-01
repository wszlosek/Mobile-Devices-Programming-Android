package pl.edu.uj.ii.szlosek.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var textbox = Textbox()
    private lateinit var assignment: HashMap<View, String>
    private lateinit var currentTextbox: TextView

    private fun initButtons() {
        clearTextbox()
        currentTextbox = findViewById<TextView>(R.id.textbox)

        textbox.numbers = listOf(
            buttonNumber0, buttonNumber1, buttonNumber2,
            buttonNumber3, buttonNumber4, buttonNumber5,
            buttonNumber6, buttonNumber7, buttonNumber8,
            buttonNumber9
        )
        textbox.operators = listOf(
            buttonAddition, buttonSubtraction,
            buttonMultiplication, buttonDivision
        )

        assignment = hashMapOf()
        var i = 0
        for (numberButton in textbox.numbers) {
            assignment[numberButton] = i.toString()
            i += 1
        }
    }

    private fun valuesToButtons() {
        val t = findViewById<TextView>(R.id.textbox)
        for (numberButton in textbox.numbers) {
            numberButton.setOnClickListener {
                if (textbox.text == "0")
                    textbox.text = ""
                textbox.text += assignment[numberButton]
                currentTextbox.text = textbox.text
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons()
        valuesToButtons()
    }

    fun buttonClick(view: android.view.View) {
        //initButtons()
       // val t = findViewById<TextView>(R.id.textbox)
       // t.text = textbox.text
    }

    fun backspace(view: android.view.View) {
        textbox.text = textbox.text.dropLast(1)
        if (textbox.text.length == 0)
            clearTextbox()

        currentTextbox.text = textbox.text
    }

    private fun clearTextbox() {
        textbox.text = "0"
    }

    fun clearTextbox(view: android.view.View) {
        clearTextbox()
        currentTextbox.text = textbox.text
    }

    fun solve(view: android.view.View) {}
}