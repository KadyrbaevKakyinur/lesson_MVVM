package com.example.lesson_mvvm.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_mvvm.R
import com.example.lesson_mvvm.databinding.ActivityTaskBinding
import com.example.lesson_mvvm.model.Task
import com.example.lesson_mvvm.viewModel.ViewModel

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private lateinit var viewModel: ViewModel
    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        task = intent.getSerializableExtra(MainActivity.UPDATE_TASK_KEY) as Task?
        if (task == null) {
            createTask()
        } else {
            initView()
        }
    }

    private fun createTask() {
        binding.buttonSave.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString(),
            )
            viewModel.addTask(data)
            finish()
        }
    }

    private fun initView() {
        with(binding) {
            etTitle.setText(task?.title)
            etDescription.setText(task?.description)
            buttonSave.text = getString(R.string.update)
            buttonSave.setOnClickListener {
                val data = task!!.copy(
                    title = binding.etTitle.text.toString(),
                    description = binding.etDescription.text.toString(),
                )
                viewModel.updateTask(data)
                finish()
            }
        }
    }
}
