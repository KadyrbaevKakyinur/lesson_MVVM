package com.example.lesson_mvvm.app


import android.app.Application
import androidx.room.Database
import androidx.room.Room

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, com.example.lesson_mvvm.database.Database::class.java, "TaskFile")
            .allowMainThreadQueries().build()
    }

    companion object {
        lateinit var database: com.example.lesson_mvvm.database.Database
    }
}
