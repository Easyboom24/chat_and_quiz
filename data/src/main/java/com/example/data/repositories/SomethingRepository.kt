package com.example.data.repositories

import com.example.data.DBProvider
import com.example.data.models.SomethingDB

class SomethingRepository {
    private val somethingDao = DBProvider.instance.somethingDao()

    companion object {
        val instance = SomethingRepository()
    }

    fun getAllSomethingData() = somethingDao.getAllSomethingData()

    suspend fun addSomething(model: SomethingDB) = somethingDao.addSomething(model)

    suspend fun deleteSomething(model: SomethingDB) = somethingDao.deleteSomething(model)

    suspend fun  updateSomething(model: SomethingDB) = somethingDao.updateSomething(model)
}