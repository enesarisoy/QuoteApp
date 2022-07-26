package com.ns.quotewithmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.quotewithmvvm.adapter.SavedAdapter
import com.ns.quotewithmvvm.data.QuoteDB
import com.ns.quotewithmvvm.databinding.FragmentSavedBinding
import com.ns.quotewithmvvm.model.Quote

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding
        get() = _binding!!
    private var savedList: List<Quote> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSavedBinding.inflate(inflater, container, false)

        val quoteDatabase: QuoteDB? = QuoteDB.getQuoteDatabase(context!!)
        savedList = quoteDatabase?.quoteDao()?.getAllQuote() as List<Quote>

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SavedAdapter(savedList)

        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}