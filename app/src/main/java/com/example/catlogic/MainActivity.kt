package com.example.catlogic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catlogic.composables.AddEatingRecordScreen
import com.example.catlogic.composables.AddHealthRecordScreen
import com.example.catlogic.composables.AddPlayingRecordScreen
import com.example.catlogic.composables.EatingRecordsScreen
import com.example.catlogic.composables.HealthRecordsScreen
import com.example.catlogic.composables.PlayingRecordsScreen
import com.example.catlogic.ui.theme.CatLogicTheme
import com.example.catlogic.viewmodel.CatActivitiesViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CatActivitiesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatLogicTheme {
                MainApp(viewModel)
            }
        }
    }
}

@Composable
fun MainApp(viewModel: CatActivitiesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            MainScreen(navController)
        }

        composable("eating_records") {
            EatingRecordsScreen(viewModel, navController)
        }

        composable("playing_records") {
            PlayingRecordsScreen(viewModel, navController)
        }

        composable("health_records") {
            HealthRecordsScreen(viewModel, navController)
        }

        composable("add_eating_record") {
            AddEatingRecordScreen(viewModel)
        }

        composable("add_playing_record") {
            AddPlayingRecordScreen(viewModel)
        }

        composable("add_health_record") {
            AddHealthRecordScreen(viewModel)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column {
        Button(
            onClick = { navController.navigate("eating_records") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Eating Records")
        }

        Button(
            onClick = { navController.navigate("playing_records") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Playing Records")
        }

        Button(
            onClick = { navController.navigate("health_records") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Health Records")
        }
    }
}

