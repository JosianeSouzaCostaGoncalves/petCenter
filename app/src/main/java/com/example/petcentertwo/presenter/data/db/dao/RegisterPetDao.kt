package com.example.petcentertwo.presenter.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterPetDao {
    @Query("SELECT * FROM RegisterPetEntity ORDER BY name ASC")
    fun getAllRegisterPet(): Flow<List<RegisterPetEntity>>

    @Insert
    suspend fun insert(registerPetEntity: RegisterPetEntity)

    @Query("DELETE FROM RegisterPetEntity WHERE id = :id")
    suspend fun delete(id: Int)

}