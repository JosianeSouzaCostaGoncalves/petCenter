package com.example.petcentertwo.presenter.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RegisterPetEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name: String,
    val type: String,
    val breed: String,
    val dateOfBrith: String
)