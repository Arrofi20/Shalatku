package com.example.shalatku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SalatKuViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SalatKuUiState())
    val uiState: StateFlow<SalatKuUiState> = _uiState.asStateFlow()

    // Not UI state: no screen reads it, so it stays a plain private variable.
    private var loadCount = 0

    fun onEvent(event: SalatKuEvent) {
        when (event) {
            is SalatKuEvent.PrayerToggled -> togglePrayer(event.name)
            SalatKuEvent.PrayersCleared -> clearPrayers()
            is SalatKuEvent.DaySelected -> selectDay(event.index)
            SalatKuEvent.TasbihIncremented -> incrementTasbih()
            SalatKuEvent.TasbihReset -> resetTasbih()
            is SalatKuEvent.RoundSizeSelected -> setRoundSize(event.size)
            SalatKuEvent.PrayerTimesRequested -> loadPrayerTimes()
        }
    }

    private fun togglePrayer(name: String) {
        _uiState.update { state ->
            val done = if (name in state.donePrayers) {
                state.donePrayers - name
            } else {
                state.donePrayers + name
            }
            state.copy(donePrayers = done)
        }
    }

    private fun clearPrayers() {
        _uiState.update { it.copy(donePrayers = emptyList()) }
    }

    private fun selectDay(index: Int) {
        _uiState.update { it.copy(selectedDay = index) }
    }

    private fun incrementTasbih() {
        _uiState.update { state ->
            val next = state.tasbihCount + 1
            if (next == state.roundSize) {
                state.copy(tasbihCount = 0, completedRounds = state.completedRounds + 1)
            } else {
                state.copy(tasbihCount = next)
            }
        }
    }

    private fun resetTasbih() {
        _uiState.update { it.copy(tasbihCount = 0, completedRounds = 0) }
    }

    private fun setRoundSize(size: Int) {
        // HW12 rule: a new target resets the current count but keeps the completed rounds.
        _uiState.update { it.copy(roundSize = size, tasbihCount = 0) }
    }

    private fun loadPrayerTimes() {
        // Already loading: a new call does nothing.
        if (_uiState.value.prayerTimes is PrayerTimesUiState.Loading) return

        loadCount++
        // Call 1 succeeds, call 2 fails, call 3 succeeds, and so on.
        val shouldFail = loadCount % 2 == 0

        _uiState.update { it.copy(prayerTimes = PrayerTimesUiState.Loading) }

        viewModelScope.launch {
            delay(1500)
            val result = if (shouldFail) {
                PrayerTimesUiState.Error("Failed to load prayer times. Please try again.")
            } else {
                PrayerTimesUiState.Success(samplePrayers)
            }
            _uiState.update { it.copy(prayerTimes = result) }
        }
    }
}