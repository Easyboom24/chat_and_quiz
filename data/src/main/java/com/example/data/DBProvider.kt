package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.daos.ISomethingDao
import com.example.data.models.SomethingDB

@Database(entities = [SomethingDB::class], version = 1)
abstract class DBProvider: RoomDatabase() {

    companion object {
        lateinit var instance: DBProvider

        fun create(context: Context){
            instance = Room.databaseBuilder(context, DBProvider::class.java, "database").build()
        }
    }

    abstract fun somethingDao(): ISomethingDao
}