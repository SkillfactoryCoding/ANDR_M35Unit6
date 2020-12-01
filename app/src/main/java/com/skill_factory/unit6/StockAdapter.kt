package com.skill_factory.unit6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.skill_factory.unit6.databinding.ListItemBinding

class StockAdapter(var data: ArrayList<Stock>) : RecyclerView.Adapter<StockAdapter.StockHolder>() {

    class StockHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val context = LayoutInflater.from(parent.context)
        return StockHolder(DataBindingUtil.inflate(context, R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.binding.stock = data[position]
        holder.binding.exRate = getExRate()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}