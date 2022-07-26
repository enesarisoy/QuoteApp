package com.ns.quotewithmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.ns.quotewithmvvm.R
import com.ns.quotewithmvvm.databinding.ActivityMainBinding
import com.ns.quotewithmvvm.model.Quote

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var quoteList: List<Quote> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            binding.fragmentContainerView.findNavController()
                .navigate(R.id.action_quoteFragment_to_savedFragment2)
        }



    }


}