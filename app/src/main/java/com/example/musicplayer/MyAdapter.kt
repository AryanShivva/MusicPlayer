package com.example.musicplayer

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.google.android.gms.analytics.ecommerce.Product
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity,val productArrayList: List<Product>)  :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //creating the view using inflater
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem, parent, false)
        return MyViewHolder(itemView,mylistener)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //data populate

        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title     //getting data
        holder.price.text = currentItem.price.toString()+"$"

        //using picaso dependency here because image is in the form of url
        Picasso.get().load(currentItem.thumbnail)
            .into(holder.image);  //image will be holded by holder.image
    }


    class MyViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        var title: TextView                        //telling them the type
        var image: ShapeableImageView
        var price : TextView


        init {
            title = itemView.findViewById(R.id.SongName)       //finding
            image = itemView.findViewById(R.id.SongImage)
            price = itemView.findViewById(R.id.SongAuthor)

            //part 2
            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
            }
        }

    }

    private lateinit var mylistener:onItemClickListener

    interface onItemClickListener{
        fun onItemClicking(position:Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mylistener = listener
    }

}