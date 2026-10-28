package com.example.shalatku

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SalatKuViewModel : ViewModel() {

    var tasbihCount by mutableIntStateOf(0)
        private set

    fun incrementTasbih() {
        tasbihCount++
    }

    fun resetTasbih() {
        tasbihCount = 0
    }
}