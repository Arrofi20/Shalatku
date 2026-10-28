package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TasbihScreen(
    count: Int,
    rounds: Int,
    roundSize: Int,
    onIncrement: () -> Unit,
    onReset: () -> Unit,
    onRoundSizeChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tasbih", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("$count", style = MaterialTheme.typography.displayLarge)
        Text("Rounds: $rounds", style = MaterialTheme.typography.titleMedium)
        Text("Target: $roundSize", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { onRoundSizeChange(33) }) { Text("33") }
            OutlinedButton(onClick = { onRoundSizeChange(99) }) { Text("99") }
            OutlinedButton(onClick = { onRoundSizeChange(100) }) { Text("100") }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onIncrement) { Text("+1") }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onReset) { Text("Reset") }
    }
}

@Preview(showBackground = true)
@Composable
fun TasbihScreenPreview() {
    TasbihScreen(
        count = 12,
        rounds = 2,
        roundSize = 33,
        onIncrement = {},
        onReset = {},
        onRoundSizeChange = {}
    )
}