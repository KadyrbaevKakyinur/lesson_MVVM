package com.example.lesson_mvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lesson_mvvm.model.Task


@Database(entities = [Task::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}
