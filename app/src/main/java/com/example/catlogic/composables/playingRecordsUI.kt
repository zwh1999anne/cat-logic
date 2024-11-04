package com.example.catlogic.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catlogic.api.PlayingActivity
import com.example.catlogic.viewmodel.CatActivitiesViewModel

@Composable
fun PlayingRecordsScreen(viewModel: CatActivitiesViewModel, navController: NavController, ) {
    val searchText by viewModel.searchText.observeAsState("")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Playing Records", style = MaterialTheme.typography.titleMedium)

        // Search Bar
        TextField(
            value = searchText,
            onValueChange = {
                viewModel.updateSearchText(it)
            },
            label = { Text("Search Playing Records") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Record Button
        Button(onClick = { navController.navigate("add_playing_record") }) {
            Text("Add Playing Record")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Playing Activity List with filtering
        val filteredLogs = viewModel.filterPlayingLog(searchText)
        PlayingActivityList(filteredLogs)
    }
}

@Composable
fun PlayingActivityList(logs: List<PlayingActivity>) {
    LazyColumn {
        items(logs.size) {
            val log = logs.get(it)
            Text("${log.date}: ${log.activityType} , Duration: ${log.duration} minutes")
        }
    }
}

@Composable
fun AddPlayingRecordScreen(viewModel: CatActivitiesViewModel) {
    var date by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var activityType by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add Playing Record", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Date Picker
        Text("Date: $date", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { DatePickerDialog(context) { selectedDate -> date = selectedDate } }) {
            Text("Select Date")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Duration
        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duration (minutes)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Activity Type
        OutlinedTextField(
            value = activityType,
            onValueChange = { activityType = it },
            label = { Text("Activity Type") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.addPlayingLog(date, duration.toIntOrNull() ?: 0, activityType)
            }
        ) {
            Text("Save Playing Record")
        }
    }
}

