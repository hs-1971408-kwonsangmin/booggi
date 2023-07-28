package com.example.mango_contents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVadapter(val context:Context,val List: MutableList<contentsModel>):RecyclerView.Adapter<RVadapter.ViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVadapter.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.rvitem,parent,false)

        return ViewHolder(v)
    }

    interface ItemClick{

        fun OnClick(view:View,position: Int)
    }
    var itemClick:ItemClick?=null

    override fun onBindViewHolder(holder: RVadapter.ViewHolder, position: Int) {

        if(itemClick!=null){
            holder.itemView?.setOnClickListener{
                v->itemClick!!.OnClick(v,position)
            }
        }
        holder.BindItems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


        fun BindItems(item:contentsModel){

            val rv_image=itemView.findViewById<ImageView>(R.id.rvimageArea)
            val rv_text=itemView.findViewById<TextView>(R.id.rvTextArea)


            rv_text.text=item.titleText
            Glide.with(context).load(item.ImageURL).into(rv_image)


        }
    }
}