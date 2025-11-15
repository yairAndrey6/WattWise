package wattwise.app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import wattwise.app.R
import wattwise.app.database.BDHelper

class AddDeviceActivity : AppCompatActivity() {

    private lateinit var db: BDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_devices)

        db = BDHelper(this)

        val nombre = findViewById<EditText>(R.id.edit_text_name)
        val modelo = findViewById<EditText>(R.id.edit_text_model)
        val watts = findViewById<EditText>(R.id.edit_text_wats)
        val tipo = findViewById<EditText>(R.id.edit_text_type)

        val btnAgregar = findViewById<Button>(R.id.btn_add_new_device)


        btnAgregar.setOnClickListener {

            val name = nombre.text.toString().trim()
            val model = modelo.text.toString().trim()
            val wattsValue = watts.text.toString().trim()
            val type = tipo.text.toString().trim()

            if (name.isEmpty() || model.isEmpty() || wattsValue.isEmpty() || type.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar número
            val wattsInt = try {
                wattsValue.toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Los watts deben ser un número válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val insertado = db.insertDevice(name, model, wattsInt,type)

            if (insertado) {
                Toast.makeText(this, "Dispositivo agregado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al guardar el dispositivo", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

