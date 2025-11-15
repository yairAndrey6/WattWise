package wattwise.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import wattwise.app.activities.*
import wattwise.app.database.BDHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db = BDHelper(this)

        val nameInput = findViewById<EditText>(R.id.edit_text_email)
        val passwordInput = findViewById<EditText>(R.id.edit_text_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        var btnRegister = findViewById<Button>(R.id.btn_register)



        btnLogin.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Debe ingresar nombre y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isValid = db.checkUser(name, password)

            if (isValid) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, IndexActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
