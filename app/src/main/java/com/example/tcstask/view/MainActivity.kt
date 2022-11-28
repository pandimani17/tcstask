package com.example.tcstask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tcstask.R
import com.example.tcstask.adapter.ProductRecyclerAdapter
import com.example.tcstask.databinding.ActivityMainBinding
import com.example.tcstask.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductRecyclerAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.state.observe(this) {
            if (!it.isLoading) {
                binding.progressbar.visibility = View.INVISIBLE
                binding.recyclerview.visibility = View.VISIBLE
                productAdapter = ProductRecyclerAdapter(it.products)
                binding.recyclerview.adapter = productAdapter
            }
        }
    }
}
