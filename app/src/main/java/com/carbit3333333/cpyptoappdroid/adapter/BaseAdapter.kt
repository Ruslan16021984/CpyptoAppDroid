package com.carbit3333333.cpyptoappdroid.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH: BaseAdapter.BaseViewHolder>: RecyclerView.Adapter<VH>() {
    var items: ArrayList<Any> = ArrayList()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
    fun getItem(position:Int) : Any{
        return items[position]
    }
    fun add(newItem: Any){
        items.add(newItem)
        Log.e("ADD_CURRENCY", "$newItem")
    }

    abstract class BaseViewHolder(protected val view: View):RecyclerView.ViewHolder(view){
        abstract fun bind(item: Any)
    }
}