package com.example.lesson_mvvm.mvvm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson_mvvm.adapter.TaskAdapter
import com.example.lesson_mvvm.databinding.ActivityMainBinding
import com.example.lesson_mvvm.model.Task
import com.example.lesson_mvvm.viewModel.TaskViewModel

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = TaskAdapter(viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.addButton.setOnClickListener {
            showAddTaskDialog()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAddTaskDialog() {
        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        builder.setTitle("Добавить задачу")
            .setView(input)
            .setPositiveButton("Добавить") { _, _ ->
                val taskTitle = input.text.toString()
                if (taskTitle.isNotEmpty()) {
                    viewModel.addTask(Task(taskTitle))
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}