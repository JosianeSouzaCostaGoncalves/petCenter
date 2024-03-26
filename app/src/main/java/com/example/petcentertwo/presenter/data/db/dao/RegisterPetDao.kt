package com.example.petcentertwo.presenter.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterPetDao {
    @Query("SELECT * FROM register_pet ORDER BY name ASC")
    fun getAllRegisterPet(): Flow<List<RegisterPetEntity>>

    @Insert
    suspend fun insert(registerPetEntity: RegisterPetEntity)

    @Query("Delete from register_pet where id = :id")
    suspend fun delete(id: Int?)

}