package com.ns.quotewithmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ns.quotewithmvvm.databinding.RecyclerRowBinding
import com.ns.quotewithmvvm.model.Quote

class QuoteAdapter(private val quoteList: List<Quote>) :
    RecyclerView.Adapter<QuoteAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.binding.apply {
            tvQuote.text = quoteList[position].q
            tvAuthor.text = quoteList[position].a
        }
    }


    override fun getItemCount(): Int = quoteList.size


}
