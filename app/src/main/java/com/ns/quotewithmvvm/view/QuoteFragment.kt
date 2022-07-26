package com.ns.quotewithmvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.ns.quotewithmvvm.adapter.QuoteAdapter
import com.ns.quotewithmvvm.data.QuoteDB
import com.ns.quotewithmvvm.databinding.FragmentQuoteBinding
import com.ns.quotewithmvvm.model.Quote
import com.ns.quotewithmvvm.service.QuoteApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding
        get() = _binding!!
    private var quoteList: List<Quote> = listOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)

        buildRetrofit()

        return binding.root
    }

    private fun buildRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(QuoteApi::class.java)

        api.getQuotes().enqueue(object : Callback<List<Quote>> {
            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                quoteList = response.body()!!
                showData(quoteList)
            }

            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                Log.e("onFailure", "onFailure: $t")
            }
        })
    }


    fun fabOnClick(position: Int) {
        binding.fab.setOnClickListener {
            Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show()
            val saved = quoteList[position]
            val quoteDatabase: QuoteDB? = QuoteDB.getQuoteDatabase(view!!.context)
            quoteDatabase?.quoteDao()?.insert(saved)

        }
    }


    private fun showData(quoteList: List<Quote>) {
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(view!!.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = QuoteAdapter(quoteList)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(binding.recyclerView)

            binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val centerView = snapHelper.findSnapView(layoutManager)
                        val pos: Int = (layoutManager as LinearLayoutManager).getPosition(centerView!!)
                        Log.e("Snapped Item Position:", "" + pos)
                        fabOnClick(pos)
                    }
                }
            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}