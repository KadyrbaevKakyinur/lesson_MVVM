package com.example.lesson_mvvm.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_mvvm.databinding.ItemTaskBinding
import com.example.lesson_mvvm.model.Task
import com.example.lesson_mvvm.viewModel.TaskViewModel


class TaskAdapter(private val viewModel: TaskViewModel) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = viewModel.taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return viewModel.taskList.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskTitle.text = task.title
            binding.taskCheckbox.isChecked = task.isCompleted
            binding.taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
                task.isCompleted = isChecked
            }
            binding.root.setOnLongClickListener {
                showDeleteTaskDialog(task)
                true
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun showDeleteTaskDialog(task: Task) {
            val builder = AlertDialog.Builder(binding.root.context)
            builder.setMessage("Удалить задачу?").setPositiveButton("Да") { _, _ ->
                viewModel.removeTask(task)
                notifyDataSetChanged()
            }.setNegativeButton("Нет", null).show()
        }
    }
}
