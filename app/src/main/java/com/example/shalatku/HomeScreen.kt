package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val days = listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min")

@Composable
fun HomeScreen(
    prayers: List<Prayer>,
    selectedDay: Int,
    onSelectDay: (Int) -> Unit,
    prayerTimes: PrayerTimesUiState,
    onRefreshPrayerTimes: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Column {
                Text("Shalatku", style = MaterialTheme.typography.headlineMedium)
                Text("Today's prayer times", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                DateStrip(selectedDay, onSelectDay)
            }
        }
        item {
            PrayerTimesSection(state = prayerTimes, onRefresh = onRefreshPrayerTimes)
        }
        items(prayers, key = { it.name }) { prayer ->
            PrayerCard(prayer)
        }
    }
}

@Composable
fun DateStrip(
    selectedDay: Int,
    onSelectDay: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        itemsIndexed(days) { index, day ->
            FilterChip(
                selected = index == selectedDay,
                onClick = { onSelectDay(index) },
                label = { Text(day) }
            )
        }
    }
}

@Composable
fun PrayerCard(prayer: Prayer) {
    Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(prayer.name, style = MaterialTheme.typography.titleMedium)
            Text(prayer.time, style = MaterialTheme.typography.titleMedium)
        }
    }
}