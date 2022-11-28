package com.example.data.repository

import com.example.data.api.ProductsApi
import com.example.data.extensions.toProduct
import com.example.domain.model.Product
import com.example.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductsApi
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {

        return api.getProducts().map { it.toProduct() }
    }

    override suspend fun insertProducts(product: Product) {
        TODO("Not yet implemented")
    }


}