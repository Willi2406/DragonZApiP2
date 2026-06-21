package com.example.dragonzapip2.presentacion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.usecase.GetPlanetsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
): ViewModel(){
    private val _state = MutableStateFlow(PlanetListUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: PlanetListEvent){
        when(event){
            is PlanetListEvent.UpdateFilters -> _state.update {
                it.copy(filterName = event.name, filterIsDestroyed = event.isDestroyed)
            }
            PlanetListEvent.Search -> loadPlanets()
        }
    }

    private fun loadPlanets(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val current = _state.value

            val result = getPlanetsUseCase(
                page = 1,
                limit = 50,
                name =  current.filterName.takeIf{it.isNotBlank()},
                isDestroyed = current.filterIsDestroyed
            )

            when(result){
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            planets = result.data?.map { dto -> dto.toDomain() } ?: emptyList()
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Loading -> { }
            }
        }
    }
}