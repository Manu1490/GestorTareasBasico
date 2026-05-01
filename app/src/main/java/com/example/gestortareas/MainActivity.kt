package com.example.gestortareas

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Lista donde guardamos todas las tareas
    private val listaTareas = mutableListOf<Tarea>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Conecta esta Activity con el XML principal
        setContentView(R.layout.activity_main)

        // Referencias a los elementos de la pantalla
        val editTextTarea = findViewById<EditText>(R.id.editTextTarea1)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar1)
        val listView = findViewById<ListView>(R.id.listViewTareas)

        // Creamos el adaptador personalizado
        val adapter = TareaAdapter()

        // Conectamos el adaptador con el ListView
        listView.adapter = adapter

        // Evento del botón Guardar
        btnGuardar.setOnClickListener {

            // Obtenemos el texto del usuario
            val texto = editTextTarea.text.toString()

            // Validamos que no esté vacío
            if (texto.isNotEmpty()) {

                // Creamos una nueva tarea (no completada)
                val nuevaTarea = Tarea(texto, false)

                // La agregamos a la lista
                listaTareas.add(nuevaTarea)

                // Actualizamos la pantalla
                adapter.notifyDataSetChanged()

                // Limpiamos el input
                editTextTarea.text.clear()

            } else {
                // Mensaje si no escribe nada
                Toast.makeText(this, "Escribe una tarea", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 🔹 Clase que representa una tarea
    data class Tarea(
        val nombre: String,      // texto de la tarea
        var completada: Boolean  // si está marcada o no
    )

    // 🔹 Adaptador personalizado (clave para la lista)
    inner class TareaAdapter : BaseAdapter() {

        // Cantidad de tareas
        override fun getCount(): Int {
            return listaTareas.size
        }

        // Devuelve una tarea
        override fun getItem(position: Int): Any {
            return listaTareas[position]
        }

        // ID de la fila
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // Crea cada fila de la lista
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            // Carga el diseño de cada tarea (item_tarea.xml)
            val view = layoutInflater.inflate(R.layout.item_tarea, parent, false)

            // Obtenemos la tarea actual
            val tarea = listaTareas[position]

            // Conectamos elementos del layout
            val check = view.findViewById<CheckBox>(R.id.checkBox)
            val texto = view.findViewById<TextView>(R.id.textArea)
            val btnEliminar = view.findViewById<Button>(R.id.btnDelete)

            // Mostramos el nombre de la tarea
            texto.text = tarea.nombre

            // Mostramos si está marcada o no
            check.isChecked = tarea.completada

            // Evento al marcar/desmarcar
            check.setOnCheckedChangeListener { _, isChecked ->
                tarea.completada = isChecked
            }

            // Evento del botón eliminar
            btnEliminar.setOnClickListener {
                listaTareas.removeAt(position)   // elimina tarea
                notifyDataSetChanged()          // actualiza lista
            }

            return view
        }
    }
}