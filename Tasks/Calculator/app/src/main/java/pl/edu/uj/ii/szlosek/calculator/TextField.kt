package pl.edu.uj.ii.szlosek.calculator

import android.view.View

class TextField {

    lateinit var text: String
    lateinit var numbers: List<View>
    lateinit var nonNumbers: List<View>
    lateinit var specialCommands: List<View>

    fun doubleAddition() {
        while (text.contains("++")) {
            text = text.replace("++", "+")
        }
    }

    fun doubleSubtraction() {
        while (text.contains("--")) {
            text = text.replace("--", "+")
        }
    }

    fun plusMinus() {
        while (text.contains("+-") || text.contains("-+")) {
            text = text.replace("+-", "-")
            text = text.replace("-+", "-")
        }
    }

    // easter egg :)
    fun klaudia(): Boolean {
        if (text == "158" || text == "158.0")
            return true

        return false
    }
}