package com.example.domain.use_case.get_products

import com.example.domain.data.repository.FakeProductRepository
import com.example.domain.model.Product
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.jupiter.api.Test



class GetProductsUseCaseTest {

    private  var fakeRepository :FakeProductRepository = FakeProductRepository()
    private  var getProducts :GetProductsUseCase = GetProductsUseCase(fakeRepository)




    @Before
fun setup(){
    fakeRepository = FakeProductRepository()
    getProducts = GetProductsUseCase(fakeRepository)

    val productToInsert = mutableListOf<Product>()
    ('a'..'z').forEachIndexed { index, c ->
        productToInsert.add(
            Product(
                id = index,
                title = c.toString(),
                price = 0.4,
                description = c.toString(),
                category = c.toString(),
                image = c.toString()
            )
        )

    }
    productToInsert.shuffle()

    runBlocking {     productToInsert.forEach { fakeRepository.insertProducts(it) }
    }

}

    @Test
    fun `check count of products`(): Unit = runBlocking {
        val products = getProducts
        val expectecd = 26
        val numberOfProducts =products.invoke()
        val list = numberOfProducts.toList()
        assertThat(expectecd == list.size)

    }

}