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
import com.example.catlogic.api.HealthActivity
import com.example.catlogic.viewmodel.CatActivitiesViewModel

@Composable
fun HealthRecordsScreen(viewModel: CatActivitiesViewModel, navController: NavController, ) {
    val searchText by viewModel.searchText.observeAsState("")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Health Records", style = MaterialTheme.typography.titleMedium)

        // Search Bar
        TextField(
            value = searchText,
            onValueChange = {
                viewModel.updateSearchText(it)
            },
            label = { Text("Search Health Records") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Record Button
        Button(onClick = { navController.navigate("add_health_record") }) {
            Text("Add Health Record")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Health Activity List with filtering
        val filteredLogs = viewModel.filterHealthLog(searchText)
        HealthActivityList(filteredLogs)
    }
}

@Composable
fun HealthActivityList(logs: List<HealthActivity>) {
    LazyColumn {
        items(logs.size) {
            val log = logs.get(it)
            Text("${log.date}: Weight: ${log.weight} kg, Notes: ${log.notes}")
        }
    }
}

@Composable
fun AddHealthRecordScreen(viewModel: CatActivitiesViewModel) {
    var date by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add Health Record", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Date Picker
        Text("Date: $date", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { DatePickerDialog(context) { selectedDate -> date = selectedDate } }) {
            Text("Select Date")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Weight
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Notes
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.addHealthLog(date, weight.toFloatOrNull() ?: 0f, notes)
            }
        ) {
            Text("Save Health Record")
        }
    }
}

