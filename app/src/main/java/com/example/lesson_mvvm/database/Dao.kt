package com.example.lesson_mvvm.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lesson_mvvm.model.Task

@Dao
interface Dao {
    @Query("SELECT * FROM Task")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM Task WHERE checkBox = 0")
    fun getTasksFalse(): List<Task>

    @Query("SELECT * FROM Task WHERE checkBox = 1")
    fun getTasksTrue(): List<Task>
}
