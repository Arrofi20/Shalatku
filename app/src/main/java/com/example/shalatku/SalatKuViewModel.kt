package com.example.shalatku

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SalatKuViewModel : ViewModel() {

    // --- Tasbih (from CP1) ---
    var tasbihCount by mutableIntStateOf(0)
        private set

    // --- Tracker ---
    private val _donePrayers = mutableStateListOf<String>()
    val donePrayers: List<String> get() = _donePrayers

    // --- Home ---
    var selectedDay by mutableIntStateOf(0)
        private set

    fun incrementTasbih() { tasbihCount++ }
    fun resetTasbih() { tasbihCount = 0 }

    fun togglePrayer(name: String) {
        if (name in _donePrayers) _donePrayers.remove(name) else _donePrayers.add(name)
    }

    fun selectDay(index: Int) {
        selectedDay = index
    }
}