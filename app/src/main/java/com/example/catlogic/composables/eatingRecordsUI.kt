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
import com.example.catlogic.api.EatingActivity
import com.example.catlogic.viewmodel.CatActivitiesViewModel

@Composable
fun EatingRecordsScreen(viewModel: CatActivitiesViewModel, navController: NavController, ) {
    val searchText by viewModel.searchText.observeAsState("")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Eating Records", style = MaterialTheme.typography.titleMedium)

        // Search Bar
        TextField(
            value = searchText,
            onValueChange = {
                viewModel.updateSearchText(it)
            },
            label = { Text("Search Eating Records") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Record Button
        Button(onClick = { navController.navigate("add_eating_record") }) {
            Text("Add Eating Record")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Eating Activity List with filtering
        val filteredLogs = viewModel.filterEatingLog(searchText)
        EatingActivityList(filteredLogs)
    }
}

@Composable
fun EatingActivityList(logs: List<EatingActivity>) {
    LazyColumn {
        items(logs.size) {
            val log = logs.get(it)
            Text("${log.date}: ${log.foodBrand} ${log.foodFlavor}, Quantity: ${log.quantity}g")
        }
    }
}

@Composable
fun AddEatingRecordScreen(viewModel: CatActivitiesViewModel) {
    var date by remember { mutableStateOf("") }
    var foodBrand by remember { mutableStateOf("") }
    var foodFlavor by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add Eating Record", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Date Picker
        Text("Date: $date", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { DatePickerDialog(context) { selectedDate -> date = selectedDate } }) {
            Text("Select Date")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Food Brand
        OutlinedTextField(
            value = foodBrand,
            onValueChange = { foodBrand = it },
            label = { Text("Food Brand") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Food Flavor
        OutlinedTextField(
            value = foodFlavor,
            onValueChange = { foodFlavor = it },
            label = { Text("Food Flavor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quantity
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.addEatingLog(date, foodBrand, foodFlavor, quantity.toIntOrNull() ?: 0)
            }
        ) {
            Text("Save Eating Record")
        }
    }
}

