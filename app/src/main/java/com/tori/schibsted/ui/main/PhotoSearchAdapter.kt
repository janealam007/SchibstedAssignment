package com.tori.schibsted.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tori.schibsted.network.data.photo.PhotoDto
import com.tori.schibsted.databinding.PhotoSearchItemBinding


class PhotoSearchAdapter : RecyclerView.Adapter<PhotoSearchAdapter.MyViewHolder>() {

    private var listener :((PhotoDto)->Unit)?=null
    private var list = mutableListOf<PhotoDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun setContentList(list: MutableList<PhotoDto>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: PhotoSearchItemBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            PhotoSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(photo:(PhotoDto)->Unit){
        listener= photo
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.photo = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}