package com.example.dragonzapip2.presentacion.planet.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.planet.usecase.GetPlanetDetailUseCase
import com.example.dragonzapip2.presentacion.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlanetViewModel @Inject constructor(
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
): ViewModel(){
    private val _state = MutableStateFlow(DetailPlanetUiState())
    val state = _state.asStateFlow()

     fun loadPlanet(id: Int){
        viewModelScope.launch {
            getPlanetDetailUseCase(id).collect { result ->
                when(result){
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, planet = result.data, error = null) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
        }
    }
}