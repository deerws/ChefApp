package com.example.chefapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chefapp.ui.theme.ChefAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppModule.provideDatabase(this)
        val recipeDao = AppModule.provideRecipeDao(database)
        val viewModelFactory = RecipeViewModelFactory(recipeDao)

        setContent {
            ChefAppTheme {
                RecipeApp(
                    viewModel = viewModel(factory = viewModelFactory),
                    sharedUrl = intent.getStringExtra(Intent.EXTRA_TEXT)
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        val database = AppModule.provideDatabase(this)
        val recipeDao = AppModule.provideRecipeDao(database)
        val viewModelFactory = RecipeViewModelFactory(recipeDao)
        setContent {
            ChefAppTheme {
                RecipeApp(
                    viewModel = viewModel(factory = viewModelFactory),
                    sharedUrl = intent.getStringExtra(Intent.EXTRA_TEXT)
                )
            }
        }
    }
}