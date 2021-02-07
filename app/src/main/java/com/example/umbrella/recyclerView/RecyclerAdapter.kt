//package com.example.umbrella.recyclerView
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.core.net.toUri
//import androidx.recyclerview.widget.RecyclerView
//import com.example.umbrella.R
//import kotlinx.android.synthetic.main.item_layout.view.*
//
//class RecyclerAdapter (private var images: List<String>) : RecyclerView.Adapter<RecyclerAdapter.ImagesViewHolder>() {
//
//     class ImagesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        fun bind(image : String) = itemView.apply {
//        rv_image.setImageURI(image.toUri())
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
//            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
//        return ImagesViewHolder(v)
//    }
//
//    override fun getItemCount(): Int {
//      return images.size
//    }
//
//    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
//        holder.bind(images[position])
//       // holder.image.setImageResource(images[position])
//    }
//
//
//}