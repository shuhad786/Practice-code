package com.example.practice_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {

    private val packingListViewModel: PackingListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "main")  {
              composable("main") { MainScreen(navController, packingListViewModel) }
              composable("nextPage") { NextScreen(navController, packingListViewModel) }
            }

        }
    }
}

@Composable
fun MainScreen(navController: NavController, viewModel: PackingListViewModel) {
  var showDialog by remember { mutableStateOf(false) }
  var itemName by remember { mutableStateOf("") }
  var category by remember { mutableStateOf("") }
  var quantity by remember { mutableStateOf("") }
  var comments by remember { mutableStateOf("") }

  Column(modifier = Modifier.padding(16.dp)) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      Button(onClick = { showDialog = true }) {
        Text("Add to packing list")
      }
      Button(onClick = { navController.navigate("nextPage")}) {
        Text("View packing list")
      }
    }

    if (showDialog) {
      AlertDialog(
        onDismissRequest = { showDialog = false },
        title = { Text("Add to Packing List") },
        text = {
          Column {
            OutlinedTextField(
              value = itemName,
              onValueChange = { itemName = it },
              label = { Text("Item Name") })
            OutlinedTextField(
              value = category,
              onValueChange = { category = it },
              label = { Text("Category") })
            OutlinedTextField(
              value = quantity,
              onValueChange = { quantity = it },
              label = { Text("Quantity") })
            OutlinedTextField(
              value = comments,
              onValueChange = { comments = it },
              label = { Text("Comments") })
          }
        },
        confirmButton = {
          Button(onClick = {
            if (itemName.isNotBlank() && category.isNotBlank() && quantity.toIntOrNull() != null) {
              viewModel.items.add(mapOf(
                "itemName" to itemName,
                "category" to category,
                "quantity" to quantity,
                "comments" to comments
              ))
              showDialog = false
              itemName = ""
              category = ""
              quantity = ""
              comments = ""
            }
          }){
            Text("Add")
          }
        }
      )
      }
    }
  }

@Composable
fun NextScreen(navController: NavController, viewModel: PackingListViewModel) {
  Column(modifier = Modifier.padding(16.dp)) {
    Button(onClick = { navController.navigateUp() }) {
      Text("Back")
    }
    Column {
      for (item in viewModel.items) {
        // Safely access the item properties
        val itemName = item["itemName"] as? String ?: "Unknown"
        val category = item["category"] as? String ?: "N/A"
        val quantity = item["quantity"] as? String ?: "0"
        val comments = item["comments"] as? String ?: "N/A"

        // Display the values using Text composable
        Text("$itemName - $category (x$quantity)\n$comments")
      }
    }
  }
}