package pl.edu.uj.ii.szlosek.calculator

import android.view.View

class TextField {

    lateinit var text: String
    lateinit var numbers: List<View>
    lateinit var nonNumbers: List<View>
    lateinit var specialCommands: List<View>

    fun klaudia(): Boolean {
        return text == "158" || text == "158.0"
    }
}