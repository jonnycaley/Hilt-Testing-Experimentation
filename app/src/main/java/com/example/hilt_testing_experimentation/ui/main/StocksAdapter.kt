package com.example.hilt_testing_experimentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt_testing_experimentation.R
import com.example.hilt_testing_experimentation.data.model.MarketSummaryDto.MarketSummaryResponse.Stock

class StocksAdapter(private val stocks: MutableList<Stock> = mutableListOf()) : RecyclerView.Adapter<StocksAdapter.ViewHolder>() {

    class ViewHolder(val name: TextView): RecyclerView.ViewHolder(name)

    fun updateItems(list: List<Stock>) {
        stocks.clear()
        stocks.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stock, parent, false) as TextView

        return ViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return stocks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = stocks[position].shortName
    }
}