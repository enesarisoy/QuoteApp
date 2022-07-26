package com.ns.quotewithmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ns.quotewithmvvm.R
import com.ns.quotewithmvvm.data.QuoteDB
import com.ns.quotewithmvvm.databinding.SavedRowBinding
import com.ns.quotewithmvvm.model.Quote

class SavedAdapter(private val savedList: List<Quote>) : RecyclerView.Adapter<SavedAdapter.VH>() {

    inner class VH(val binding: SavedRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(SavedRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            tvSavedQuote.text = savedList[position].q
            tvSavedAuth.text = savedList[position].a

            ivVert.setOnClickListener {
                val popupMenu = PopupMenu(holder.itemView.context, it)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.delete_menu -> {
                            Toast.makeText(holder.itemView.context, "Deleted", Toast.LENGTH_SHORT)
                                .show()
                            val deleted = savedList[position]
                            val quoteDatabase: QuoteDB? =
                                QuoteDB.getQuoteDatabase(holder.itemView.context)
                            quoteDatabase?.quoteDao()?.delete(deleted)
                            true
                        }
                        else -> false

                    }
                }
                popupMenu.inflate(R.menu.popup_menu)
                popupMenu.show()
            }
        }
    }

    override fun getItemCount(): Int = savedList.size
}