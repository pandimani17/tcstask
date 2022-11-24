package com.example.domain.use_case.get_products

import android.content.res.Resources
import com.example.domain.common.Resource
import com.example.domain.model.Product
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> =flow{
        try {
            emit(Resource.Loading())
            val products = repository.getProducts()
            val sortedlist: List<Product> = products.sortedBy { it.title?.toString() }
            emit(Resource.Success(sortedlist))
        }catch (e: IOException){
            emit(Resource.Error("check your internet"))
        }
    }
}