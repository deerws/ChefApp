package com.example.chefapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val reelUrl: String?,
    val createdAt: Long = System.currentTimeMillis()
)