package com.example.data.api

import com.example.data.model.ProductModel
import retrofit2.http.GET

interface ProductsApi {

    @GET("/products/")
    suspend fun getProducts() : List<ProductModel>
}