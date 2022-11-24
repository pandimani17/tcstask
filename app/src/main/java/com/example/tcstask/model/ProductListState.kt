package com.example.tcstask.model

import com.example.domain.model.Product

data class ProductListState(
    val isLoading:Boolean =false,
    val products :List<Product> = emptyList(),
    val error :String = "")
