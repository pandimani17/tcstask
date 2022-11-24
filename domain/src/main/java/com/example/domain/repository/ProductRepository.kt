package com.example.domain.repository

import com.example.domain.model.Product

interface ProductRepository {

    suspend fun getProducts():List<Product>

    suspend fun insertProducts(product : Product)

}