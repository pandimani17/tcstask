package com.example.data.extensions

import com.example.data.model.ProductModel
import com.example.domain.model.Product

fun ProductModel.toProduct() :Product{
    return Product(id, title, price, description, category, image)
}