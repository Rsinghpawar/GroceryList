package com.digicolor.propertyassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.digicolor.propertyassignment.presentation.groceryHome.GroceryHomeScreen
import com.digicolor.propertyassignment.presentation.groceryHome.GroceryHomeScreenViewmodel
import com.digicolor.propertyassignment.presentation.ui.theme.PropertyAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PropertyAssignmentTheme {
                val vm: GroceryHomeScreenViewmodel = hiltViewModel()
                GroceryHomeScreen(vm)
            }
        }
    }
}