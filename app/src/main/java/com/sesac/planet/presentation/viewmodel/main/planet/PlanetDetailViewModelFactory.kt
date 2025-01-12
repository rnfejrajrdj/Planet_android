package com.sesac.planet.presentation.viewmodel.main.planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sesac.planet.domain.usecase.main.planet.GetPlanetDetailInfoUseCase

class PlanetDetailViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlanetDetailViewModel::class.java)){
            return PlanetDetailViewModel(GetPlanetDetailInfoUseCase()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel from ExampleViewModelFactory")
    }
}