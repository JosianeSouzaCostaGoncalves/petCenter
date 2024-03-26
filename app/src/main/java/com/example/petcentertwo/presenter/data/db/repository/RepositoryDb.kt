package com.example.petcentertwo.presenter.data.db.repository

import com.example.petcentertwo.presenter.data.db.dao.RegisterPetDao
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity
import com.example.petcentertwo.presenter.repository.Repository
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.Async

class RepositoryDb(
    val registerPetDao: RegisterPetDao
) {
    fun getAllRegisterPet(): Flow<List<RegisterPetEntity>> {

        return registerPetDao.getAllRegisterPet()
    }
    suspend fun insert(registerPetEntity: RegisterPetEntity) {
        registerPetDao.insert(registerPetEntity)
    }
    suspend fun delete(id: Int) {
        registerPetDao.delete(id)
    }

}