package wattwise.app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import wattwise.app.R
import wattwise.app.database.BDHelper

class RegisterActivity : AppCompatActivity() {

    private lateinit var db: BDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        db = BDHelper(this)

        val name = findViewById<EditText>(R.id.edit_text_name)
        val lastname = findViewById<EditText>(R.id.edit_text_lastname)
        val phone = findViewById<EditText>(R.id.edit_text_phone)
        val password = findViewById<EditText>(R.id.edit_text_password)
        val btnRegister = findViewById<Button>(R.id.btn_register)

        btnRegister.setOnClickListener {
            val sName = name.text.toString()
            val sLastname = lastname.text.toString()
            val sPhone = phone.text.toString()
            val sPassword = password.text.toString()

            if (sName.isEmpty() || sLastname.isEmpty() || sPhone.isEmpty() || sPassword.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = db.insertUser(sName, sLastname, sPhone, sPassword)

            if (success) {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                finish() // Cierra Register y vuelve al Login
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_LONG).show()
            }
        }



    }



}