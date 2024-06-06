package com.feroxdev.closetApp.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.model.Collection

class CollectionAdapter(
    private val onClick: (Collection) -> Unit
) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION
    private var collectionList = listOf<Collection>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val collectionNameTextView: TextView = itemView.findViewById(R.id.collectionNameTextView)

        fun bind(collection: Collection) {
            collectionNameTextView.text = collection.collectionName
            itemView.setOnClickListener {
                onClick(collection)
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(adapterPosition)
            }
            itemView.setBackgroundColor(if (adapterPosition == selectedPosition) Color.LTGRAY else Color.TRANSPARENT)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collectionList[position])
    }

    override fun getItemCount() = collectionList.size

    fun submitList(list: List<Collection>) {
        collectionList = list
        notifyDataSetChanged()
    }
}