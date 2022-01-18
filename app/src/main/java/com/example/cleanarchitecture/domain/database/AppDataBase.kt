package com.example.cleanarchitecture.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.main.dto.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

}