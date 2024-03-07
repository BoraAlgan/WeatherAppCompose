package com.example.weatherappcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.data.locale.SavedLocations
import com.example.weatherappcompose.data.locale.domain.DeleteUseCase
import com.example.weatherappcompose.data.locale.domain.GetAllUseCase
import com.example.weatherappcompose.data.locale.domain.InsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor(

    private val getAllUseCase: GetAllUseCase,
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase


) : ViewModel() {

    private val _myState = MutableStateFlow<List<SavedLocations>>(emptyList())
    val mystate: StateFlow<List<SavedLocations>> = _myState.asStateFlow()


    fun getAllLocations() {
        getAllUseCase.getAllData(
            onFailure = {

            },
            onSuccess = {
                _myState.value = it
            }
        )
            .launchIn(viewModelScope)
    }

    fun insertUseCase(location: SavedLocations) {
        insertUseCase.insertData(
            locations = location,
            onFailure = {},
            onSuccess = {
                getAllLocations()
            }
        )
            .launchIn(viewModelScope)

    }

    fun deleteLocation(location: SavedLocations) {
        deleteUseCase.deleteData(
            locations = location,
            onFailure = {},
            onSuccess = {
                getAllLocations()
            }
        )
            .launchIn(viewModelScope)
    }
}


