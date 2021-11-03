package pl.edu.uj.ii.szlosek.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalInvalidExpressionException
import com.notkamui.keval.KevalInvalidSymbolException
import com.notkamui.keval.KevalZeroDivisionException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var textField = TextField()
    private lateinit var assignment: HashMap<View, String>
    private lateinit var currentTextbox: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtons()
        valuesToButtons()
    }

    @SuppressLint("SetTextI18n")
    private fun textSynchronization() {
        if (textField.text[0] == '0' && textField.text.length > 1)
            currentTextbox.text = textField.text.substring(1, textField.text.length)
        else
            currentTextbox.text = textField.text
    }

    private fun createButtonsDictionary() {
        assignment = hashMapOf()
        var i = 0
        for (numberButton in textField.numbers) {
            assignment[numberButton] = i.toString()
            i += 1
        }

        assignment[buttonAddition] = "+"
        assignment[buttonSubtraction] = "-"
        assignment[buttonMultiplication] = "×"
        assignment[buttonDivision] = "÷"
        assignment[buttonSeparator] = "."
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
        textField.specialCommands = listOf(
            buttonEqual, buttonBackspace, buttonClearAll
        )

        createButtonsDictionary()
    }

    private fun valuesToButtons() {
        for (numberButton in textField.numbers) {
            numberButton.setOnClickListener {
                if (textField.text == "0")
                    textField.text = ""
                textField.text += assignment[numberButton]
                textSynchronization()
            }
        }

        for (nonNumberButton in textField.nonNumbers) {
            nonNumberButton.setOnClickListener {
                textField.text += assignment[nonNumberButton]
                textSynchronization()
            }
        }
    }

    fun backspace(view: View) {
        textField.text = textField.text.dropLast(1)
        if (textField.text.isEmpty())
            clearTextField()

        textSynchronization()
    }

    private fun clearTextField() {
        textField.text = "0"
    }

    fun clearTextField(view: View) {
        clearTextField()
        textSynchronization()
    }

    @SuppressLint("SetTextI18n")
    fun solve(view: View) {

        textField.doubleSubtraction()
        textField.doubleAddition()
        textField.plusMinus()

        try {
            textField.text = Keval.eval(
                textField.text.replace(" ", "")
                    .replace("×", "*").replace("÷", "/")
            ).toString()

            if (textField.text[0] == '-')
                textField.text = "0" + textField.text

            textSynchronization()

        } catch (e: KevalZeroDivisionException) {
            currentTextbox.text = "#ERROR"
            Toast.makeText(
                this@MainActivity,
                "You can't divide by zero!",
                Toast.LENGTH_SHORT
            ).show()
            clearTextField()

        } catch (e: KevalInvalidExpressionException) {
            currentTextbox.text = "#ERROR"
            Toast.makeText(
                this@MainActivity,
                "Expression is invalid",
                Toast.LENGTH_SHORT
            ).show()
            clearTextField()

        } catch (e: KevalInvalidSymbolException) {
            currentTextbox.text = "#ERROR"
            Toast.makeText(
                this@MainActivity,
                "Expression contains an invalid symbol",
                Toast.LENGTH_SHORT
            ).show()
            clearTextField()
        }

    }
}