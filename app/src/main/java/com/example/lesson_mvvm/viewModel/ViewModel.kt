package com.example.lesson_mvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson_mvvm.model.Task
import com.example.lesson_mvvm.app.App

class ViewModel : ViewModel() {

    private val dao = App.database.dao()
    private var task = dao.getAll().toMutableList()
    var list = MutableLiveData(task)
    var lists: LiveData<MutableList<Task>> = list
    fun getTask() {
        task = dao.getAll().toMutableList()
        list.value = task
        Log.e("ololo", "getTask: ${list.value}")
    }

    fun addTask(task: Task) {
        dao.insert(task)
    }

    fun updateTask(task: Task) {
        dao.update(task)
    }

    fun deleteTask(task: Task) {
        dao.delete(task)
    }

    fun checkedTask(task: Task, isChecked: Boolean) {
        val data = task.copy(checkBox = isChecked)
        Log.e("ololo", "checkedTask: $data")
        dao.update(data)
    }

    fun filtrateFalseTasks() {
        task = dao.getTasksFalse().toMutableList()
        list.value = task
        Log.e("ololo", "filtrateFalseTasks: ${list.value}")
    }

    fun filtrateTrueTasks() {
        task = dao.getTasksTrue().toMutableList()
        list.value = task
        Log.e("ololo", "filtrateTrueTasks: ${list.value}")
    }
}
