package com.example.chefapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeDao: RecipeDao) : ViewModel() {
    val recipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()

    fun saveRecipe(title: String, description: String, reelUrl: String?) {
        viewModelScope.launch {
            val recipe = Recipe(
                title = title,
                description = description,
                reelUrl = reelUrl
            )
            recipeDao.insert(recipe)
        }
    }
}

class RecipeViewModelFactory(private val recipeDao: RecipeDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(recipeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}