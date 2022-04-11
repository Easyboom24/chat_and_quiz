package com.example.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.models.SomethingDB

@Dao
interface ISomethingDao {

    @Query("SELECT * FROM something_table")
    fun getAllSomethingData(): LiveData<List<SomethingDB>>

    @Insert
    suspend fun addSomething(model: SomethingDB)

    @Delete
    suspend fun deleteSomething(model: SomethingDB)

    @Update
    suspend fun updateSomething(model: SomethingDB)
}