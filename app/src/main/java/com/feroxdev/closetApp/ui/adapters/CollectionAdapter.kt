package com.feroxdev.closetApp.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.model.Collection

// Adaptador para manejar una lista de colecciones en un RecyclerView
class CollectionAdapter(
    private val onClick: (Collection) -> Unit
) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION
    private var collectionList = listOf<Collection>()

    // ViewHolder interno que representa los elementos de la vista
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val collectionNameTextView: TextView = itemView.findViewById(R.id.collectionNameTextView)

        // Vincula los datos de una colección a la vista
        fun bind(collection: Collection) {
            collectionNameTextView.text = collection.collectionName
            itemView.setOnClickListener {
                onClick(collection)
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(adapterPosition)
            }
            itemView.setBackgroundColor(if (adapterPosition == selectedPosition) Color.DKGRAY else Color.TRANSPARENT)
        }
    }

    // Crea nuevas vistas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
        return ViewHolder(view)
    }

    // Reemplaza el contenido de una vista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collectionList[position])
    }

    // Devuelve el tamaño de la lista de datos
    override fun getItemCount() = collectionList.size

    // Método para actualizar la lista de colecciones y notificar al adaptador
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Collection>) {
        collectionList = list
        notifyDataSetChanged()
    }
}