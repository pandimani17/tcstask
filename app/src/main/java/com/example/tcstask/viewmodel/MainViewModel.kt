package com.example.tcstask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.domain.common.Resource
import com.example.domain.use_case.get_products.GetProductsUseCase
import com.example.tcstask.model.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
)  : ViewModel(){
    private val _state = MutableLiveData<ProductListState>()
    val state :  LiveData<ProductListState> = _state

    init {
        getProducts()
    }

    private fun getProducts(){
        getProductsUseCase().onEach { result ->
  when(result){
    is Resource.Success ->{
        _state.value = ProductListState(products = result.data ?: emptyList())
    }
      is Resource.Error ->{
          _state.value = ProductListState(error = result.message ?:"unexpected error")
      }
      is Resource.Loading ->{
          _state.value = ProductListState(true)
      }
  }
        }.launchIn(viewModelScope)
    }


}