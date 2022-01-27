package pl.edu.uj.ii.szlosek.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

   /* fun fromRegisterToLogin(view: android.view.View) {

        val newLogin = findViewById<EditText>(R.id.loginInRegister)
        val newPassword = findViewById<EditText>(R.id.passwordInRegister)
        val repeatPassword = findViewById<EditText>(R.id.repeatPasswordInRegister)

        if (newLogin.text.isNotEmpty() && newPassword.text.isNotEmpty()
            && newPassword.text.toString() == repeatPassword.text.toString()) {
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

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        } else if (newLogin.text.isEmpty() || newPassword.text.isEmpty() || repeatPassword.text.isEmpty()) {
            Toast.makeText(this, "Login or password is too short!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Password and repeat password must be the same!", Toast.LENGTH_SHORT).show()
        }
    } */
}