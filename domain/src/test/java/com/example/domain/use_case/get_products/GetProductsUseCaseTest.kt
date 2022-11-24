package com.example.domain.use_case.get_products

import com.example.domain.common.Resource
import com.example.domain.data.repository.FakeProductRepository
import com.example.domain.model.Product
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetProductsUseCaseTest {

    private lateinit var fakeRepository :FakeProductRepository
    private lateinit var getProducts :GetProductsUseCase




    @BeforeAll
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
    fun `check null`(): Unit = runBlocking {
        val products = getProducts
        val flow = products.invoke()
        flow.collect { result: Resource<List<Product>> ->
            result.data?.forEach{
                assertThat(it).isNotNull()
            }
            }
        }
    }

