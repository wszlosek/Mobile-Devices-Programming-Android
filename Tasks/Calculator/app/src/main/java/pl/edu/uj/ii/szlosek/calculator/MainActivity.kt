package pl.edu.uj.ii.szlosek.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.notkamui.keval.Keval
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var textField = TextField()
    private lateinit var assignment: HashMap<View, String>
    private lateinit var currentTextbox: TextView

    private fun createButtonsDictionary() {
        assignment = hashMapOf()
        var i = 0
        for (numberButton in textField.numbers) {
            assignment[numberButton] = i.toString()
            i += 1
        }

        assignment.put(buttonAddition, "+")
        assignment.put(buttonSubtraction, "-")
        assignment.put(buttonMultiplication, "×")
        assignment.put(buttonDivision, "÷")
        assignment.put(buttonSeparator, ".")
    }

    private fun initButtons() {
        clearTextField()
        currentTextbox = findViewById(R.id.textbox)

        textField.numbers = listOf(
            buttonNumber0, buttonNumber1, buttonNumber2,
            buttonNumber3, buttonNumber4, buttonNumber5,
            buttonNumber6, buttonNumber7, buttonNumber8,
            buttonNumber9, buttonSeparator
        )
        textField.nonNumbers = listOf(
            buttonAddition, buttonSubtraction,
            buttonMultiplication, buttonDivision
        )

        createButtonsDictionary()
    }

    private fun valuesToButtons() {
        for (numberButton in textField.numbers) {
            numberButton.setOnClickListener {
                if (textField.text == "0")
                    textField.text = ""
                textField.text += assignment[numberButton]
                currentTextbox.text = textField.text
            }
        }

        for (nonNumberButton in textField.nonNumbers) {
            nonNumberButton.setOnClickListener {
                textField.text += assignment[nonNumberButton]
                currentTextbox.text = textField.text
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtons()
        valuesToButtons()
    }

    fun backspace(view: android.view.View) {
        textField.text = textField.text.dropLast(1)
        if (textField.text.isEmpty())
            clearTextField()

        currentTextbox.text = textField.text
    }

    private fun clearTextField() {
        textField.text = "0"
    }

    fun clearTextField(view: android.view.View) {
        clearTextField()
        currentTextbox.text = textField.text
    }

    fun solve(view: android.view.View) {
        println( currentTextbox.text.toString().replace(" ","")
            .replace("×", "*").replace("÷", "/"))
        currentTextbox.text = Keval.eval(
            currentTextbox.text.toString().replace(" ","")
                .replace("×", "*").replace("÷", "/")
        ).toString()

        if (currentTextbox.text.toString()[0] == '-') {
            currentTextbox.text = "0" +  currentTextbox.text.toString()
        }
        textField.text = currentTextbox.text.toString()
    }
}