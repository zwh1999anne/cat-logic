package com.example.catlogic.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catlogic.api.CatActivityBuilder
import com.example.catlogic.api.EatingActivity
import com.example.catlogic.api.HealthActivity
import com.example.catlogic.api.PlayingActivity
import com.example.catlogic.api.RetrofitServiceInstance
import kotlinx.coroutines.launch

class CatActivitiesViewModel: ViewModel() {
    private val _eatingLogs = MutableLiveData<List<EatingActivity>>()
    val eatingLogs: LiveData<List<EatingActivity>> get() = _eatingLogs

    private val _playingLogs = MutableLiveData<List<PlayingActivity>>()
    val playingLogs: LiveData<List<PlayingActivity>> get() = _playingLogs

    private val _healthLogs = MutableLiveData<List<HealthActivity>>()
    val healthLogs: LiveData<List<HealthActivity>> get() = _healthLogs

    private val _searchText = MutableLiveData<String>("")
    val searchText: LiveData<String> get() = _searchText

    init {
        fetchEatingLogs()
        fetchPlayingLogs()
        fetchHealthLogs()
    }

    private fun fetchEatingLogs() {
        viewModelScope.launch {
            try {
                _eatingLogs.value = RetrofitServiceInstance.api.getEatingLogs()
            } catch (e: Exception) {
                Log.d(TAG, "fetch eating failed, error message: ${e.message}")
            }
        }
    }

    private fun fetchPlayingLogs() {
        viewModelScope.launch {
            try {
                _playingLogs.value = RetrofitServiceInstance.api.getPlayingLogs()
            } catch (e: Exception) {
                Log.d(TAG, "fetch playing failed, error message: ${e.message}")
            }
        }
    }

    private fun fetchHealthLogs() {
        viewModelScope.launch {
            try {
                _healthLogs.value = RetrofitServiceInstance.api.getHealthLogs()
            } catch (e: Exception) {
                Log.d(TAG, "fetch health failed, error message: ${e.message}")
            }
        }
    }

    fun addEatingLog(date: String, brand: String, flavor: String, quantity: Int) {
        val eatingLog = CatActivityBuilder()
            .setDate(date)
            .setEatingDetails(brand, flavor, quantity)
            .build() as EatingActivity
        viewModelScope.launch {
            try {
                RetrofitServiceInstance.api.postEatingLog(eatingLog)
                fetchEatingLogs()
            } catch(e: Exception) {
                Log.d(TAG, "add eating failed, error message ${e.message}")
            }
        }
    }

    fun addPlayingLog(date: String, duration: Int, activityType: String) {
        val playingLog = CatActivityBuilder()
            .setDate(date)
            .setPlayingDetails(duration, activityType)
            .build() as PlayingActivity
        viewModelScope.launch {
            try {
                RetrofitServiceInstance.api.postPlayingLog(playingLog)
                fetchPlayingLogs()
            } catch(e: Exception) {
                Log.d(TAG, "add playing failed, error message ${e.message}")
            }
        }
    }

    fun addHealthLog(date: String, weight: Float, notes: String) {
        val healthLog = CatActivityBuilder()
            .setDate(date)
            .setHealthDetails(weight, notes)
            .build() as HealthActivity
        viewModelScope.launch {
            try {
                RetrofitServiceInstance.api.postHealthLog(healthLog)
                fetchHealthLogs()
            } catch(e: Exception) {
                Log.d(TAG, "add health failed, error message ${e.message}")
            }
        }
    }

    fun updateSearchText(updatedText: String) {
        _searchText.value = updatedText
    }

    fun filterEatingLog(searchText: String): List<EatingActivity> {
        if (searchText.isEmpty()) {
            return _eatingLogs.value ?: emptyList()
        }
        return _eatingLogs.value?.filter {
            it.foodBrand.contains(searchText, ignoreCase = true) || it.foodFlavor.contains(searchText, ignoreCase = true)
        } ?: emptyList()
    }

    fun filterPlayingLog(searchText: String): List<PlayingActivity> {
        if (searchText.isEmpty()) {
            return _playingLogs.value ?: emptyList()
        }
        return _playingLogs.value?.filter {
            it.activityType.contains(searchText, ignoreCase = true)
        } ?: emptyList()
    }

    fun filterHealthLog(searchText: String): List<HealthActivity> {
        if (searchText.isEmpty()) {
            return _healthLogs.value ?: emptyList()
        }
        return _healthLogs.value?.filter {
            it.notes.contains(searchText, ignoreCase = true)
        } ?: emptyList()
    }

    companion object {
        private const val TAG = "CatActivitiesViewModel"
    }
}