package com.example.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PREFERENCES_KEY = "user_data"
        private const val NOMBRE = "nombre"
        private const val RUT = "rut"
        private const val CORREO = "correo"
        
    }
    private lateinit var editTextNombre: EditText
    private lateinit var editTextRut: EditText
    private lateinit var editTextCorreo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextRut = findViewById(R.id.editTextRut)
        editTextCorreo = findViewById(R.id.editTextCorreo)

        val btnSave = findViewById<Button>(R.id.btnGuardar)
        val btnSearch = findViewById<Button>(R.id.btnBuscar)
        val preferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)


        editTextRut.setText(preferences.getString(RUT, ""))
        editTextNombre.setText(preferences.getString(NOMBRE, ""))
        editTextCorreo.setText(preferences.getString(CORREO, ""))

        btnSave.setOnClickListener {


            val rut = editTextRut.text.toString()
            val nombre = editTextNombre.text.toString()
            val correo = editTextCorreo.text.toString()

            val editor = preferences.edit()
            editor.putString(RUT, rut)
            editor.putString(NOMBRE, nombre)
            editor.putString(CORREO, correo)
            editor.apply()


            editTextRut.text.clear()
            editTextNombre.text.clear()
            editTextCorreo.text.clear()

            Toast.makeText(this, " SharedPreferences guardados", Toast.LENGTH_SHORT).show()
        }

        btnSearch.setOnClickListener {

            val rutToSearch = editTextRut.text.toString()
            val savedRut = preferences.getString(RUT, "")

            if (rutToSearch == savedRut) {
                editTextNombre.setText(preferences.getString(NOMBRE, ""))
                editTextCorreo.setText(preferences.getString(CORREO, ""))
            } else {
                showAlert("no encontrado")
            }
        }


    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }
}
