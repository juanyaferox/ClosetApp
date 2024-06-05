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

class ImageAdapter(private val imageSourceList: List<ImageSource>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageSource = imageSourceList[position]
        val imageUri = Uri.parse(imageSource.path)
        holder.imageView.setImageURI(imageUri)
        holder.imageView.layoutParams = defineMargin(holder, position)
    }

    override fun getItemCount(): Int {
        return imageSourceList.size
    }

    fun Int.dpToPx(context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    fun defineMargin(holder: ImageViewHolder, position: Int) : ViewGroup.MarginLayoutParams {
        val layoutParams = holder.imageView.layoutParams as ViewGroup.MarginLayoutParams

        layoutParams.topMargin = 20.dpToPx(holder.itemView.context)
        //logica para muestreo de imagenes "con sentido" en el recyclerview, mantiendo margenes correctamente
            if (position % 2 == 0) {
                layoutParams.leftMargin = 20.dpToPx(holder.itemView.context)
                layoutParams.rightMargin = 15.dpToPx(holder.itemView.context)
                if (position == imageSourceList.lastIndex || position == imageSourceList.lastIndex - 1){
                    layoutParams.bottomMargin = 20.dpToPx(holder.itemView.context)
                }
            }
            else {
                layoutParams.rightMargin = 20.dpToPx(holder.itemView.context)
                layoutParams.leftMargin = 15.dpToPx(holder.itemView.context)
            }

        return layoutParams
    }
}