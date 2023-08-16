package com.example.lesson_mvvm.viewModel

import androidx.lifecycle.ViewModel
import com.example.lesson_mvvm.model.Task

class TaskViewModel : ViewModel() {
    val taskList = mutableListOf<Task>()
    fun addTask(task: Task) {
        taskList.add(task)
    }


    fun removeTask(task: Task) {
        taskList.remove(task)
    }
}
