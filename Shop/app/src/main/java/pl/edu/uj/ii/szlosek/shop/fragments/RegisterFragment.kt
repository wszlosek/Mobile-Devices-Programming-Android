package pl.edu.uj.ii.szlosek.shop.fragments

import android.content.Intent
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import pl.edu.uj.ii.szlosek.shop.Login
import pl.edu.uj.ii.szlosek.shop.R
import pl.edu.uj.ii.szlosek.shop.builds.createLoginData
import pl.edu.uj.ii.szlosek.shop.builds.createUser
import pl.edu.uj.ii.szlosek.shop.builds.getLoginData
import pl.edu.uj.ii.szlosek.shop.models.LoginData
import pl.edu.uj.ii.szlosek.shop.models.User

class RegisterFragment : Fragment(R.layout.fragment_register) {
    override fun onStart() {
        super.onStart()

        val buttonRegister = (view?.findViewById(R.id.buttonRegisterInRegister)) as Button

        buttonRegister.setOnClickListener {
            val newLogin = view?.findViewById<EditText>(R.id.loginInRegister)
            val newPassword = view?.findViewById<EditText>(R.id.passwordInRegister)
            val repeatPassword = view?.findViewById<EditText>(R.id.repeatPasswordInRegister)

            if (newLogin?.text!!.isNotEmpty() && newPassword?.text!!.isNotEmpty()
                && newPassword?.text.toString() == repeatPassword?.text.toString()) {
                var n: Int
                runBlocking {
                    withContext(Dispatchers.IO) {
                        n = getLoginData().size
                    }
                }

                val loginData = LoginData(n + 1,
                    newLogin.text.toString(), newPassword.text.toString())
                val newUser = User(n + 1, newLogin.text.toString(), "", "", "")

                runBlocking {
                    withContext(Dispatchers.IO) {
                        createLoginData(loginData)
                        createUser(newUser)
                    }
                }

                val action =
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                view!!.findNavController().navigate(action)
            }
        }
    }
}