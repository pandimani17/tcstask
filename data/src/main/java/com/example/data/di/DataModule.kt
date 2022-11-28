package com.example.data.di

import com.example.data.api.ProductsApi
import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.common.Constants.BASE_URL
import com.example.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductsApi): ProductRepository {
        return ProductRepositoryImpl(api)

    }
}