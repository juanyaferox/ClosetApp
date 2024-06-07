package com.feroxdev.closetApp.ui.adapters

import android.content.Context
import android.net.Uri
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.ui.fragments.common.ImagesRecyclerViewFragment

// Adaptador para manejar una lista de imagenes en un RecyclerView
class ImageAdapter(private val imageSourceList: List<ImageSource>, private val itemClickListener: ImagesRecyclerViewFragment) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    // Interfaz para manejar clics en los elementos
    interface OnItemClickListener {
        fun onItemClick(idImage: Int)
    }

    // ViewHolder interno que representa los elementos de la vista
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            // Establece un listener en el elemento de vista
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val idImage = imageSourceList[position].idImage
                itemClickListener.onItemClick(idImage)
            }
        }
    }

    // Crea nuevas vistas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    // Reemplaza el contenido de una vista
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageSource = imageSourceList[position]
        val imageUri = Uri.parse(imageSource.path)
        holder.imageView.setImageURI(imageUri)
        holder.imageView.layoutParams = defineMargin(holder, position)
    }

    // Devuelve el tamaño de la lista de datos
    override fun getItemCount(): Int {
        return imageSourceList.size
    }

    // Extensión para convertir dp a px
    fun Int.dpToPx(context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    // Define los márgenes para cada imagen en el RecyclerView
    fun defineMargin(holder: ImageViewHolder, position: Int) : ViewGroup.MarginLayoutParams {
        val layoutParams = holder.imageView.layoutParams as ViewGroup.MarginLayoutParams

        layoutParams.topMargin = 20.dpToPx(holder.itemView.context)
            if (position % 2 == 0) {
                layoutParams.leftMargin = 20.dpToPx(holder.itemView.context)
                layoutParams.rightMargin = 15.dpToPx(holder.itemView.context)
                if (position == imageSourceList.lastIndex-1 || position == imageSourceList.lastIndex)
                    layoutParams.bottomMargin = 20.dpToPx(holder.itemView.context)
            }
            else {
                layoutParams.rightMargin = 20.dpToPx(holder.itemView.context)
                layoutParams.leftMargin = 15.dpToPx(holder.itemView.context)
                if (position == imageSourceList.lastIndex)
                    layoutParams.bottomMargin = 20.dpToPx(holder.itemView.context)
            }

        return layoutParams
    }
}