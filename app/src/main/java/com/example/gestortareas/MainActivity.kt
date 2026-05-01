package com.example.gestortareas
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Lista donde guardamos las tareas en memoria
    private val listaTareas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Conecta esta clase con el XML que hicimos
        setContentView(R.layout.activity_main)

        // Referencias a los elementos de la pantalla usando sus ID
        val editTextTarea = findViewById<EditText>(R.id.editTextTarea1)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar1)
        val listView = findViewById<ListView>(R.id.listViewTareas)

        // Adaptador: conecta la lista de datos con la ListView visual
        val adapterTareas = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaTareas
        )

        // Asignamos el adaptador al ListView
        listView.adapter = adapterTareas

        // Evento cuando se hace click en el botón Guardar
        btnGuardar.setOnClickListener {

            // Obtener el texto que escribió el usuario
            val tarea = editTextTarea.text.toString()

            // Validar que no esté vacío
            if (tarea.isNotEmpty()) {

                // Agregar la tarea a la lista
                listaTareas.add(tarea)

                // Notificar que la lista cambió para que se actualice la pantalla
                adapterTareas.notifyDataSetChanged()

                // Limpiar el campo de texto
                editTextTarea.text.clear()

            } else {
                // Mostrar mensaje si está vacío
                Toast.makeText(this, "Escribe una tarea", Toast.LENGTH_SHORT).show()
            }
        }
    }
}