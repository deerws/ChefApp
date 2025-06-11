package com.example.chefapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var database: RecipeDatabase
    private lateinit var dao: RecipeDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase::class.java).build()
        dao = database.recipeDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testInsertAndRetrieveRecipe() = runBlocking {
        val recipe = Recipe(title = "Teste", description = "Descrição", reelUrl = null)
        dao.insert(recipe)
        val recipes = dao.getAllRecipes().first()
        assertEquals(1, recipes.size)
        assertEquals("Teste", recipes[0].title)
        assertEquals("Descrição", recipes[0].description)
    }
}