package com.example.domain.data.repository

import com.example.domain.model.Product
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.flow

class FakeProductRepository : ProductRepository {

    private val products  = mutableListOf<Product>()
    override suspend fun getProducts(): List<Product> {
             return products
    }

    override suspend fun insertProducts(product: Product) {
        products.add(product)
    }


}