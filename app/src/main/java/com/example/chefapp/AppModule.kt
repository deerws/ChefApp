package com.example.chefapp

import android.content.Context
import androidx.room.Room

object AppModule {
    private var database: RecipeDatabase? = null

    fun provideDatabase(context: Context): RecipeDatabase {
        return database ?: synchronized(this) {
            database ?: Room.databaseBuilder(
                context.applicationContext,
                RecipeDatabase::class.java,
                "recipe_database"
            ).build().also { database = it }
        }
    }

    fun provideRecipeDao(database: RecipeDatabase): RecipeDao {
        return database.recipeDao()
    }
}