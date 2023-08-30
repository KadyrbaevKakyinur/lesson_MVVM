package com.example.lesson_mvvm.adapter


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_mvvm.databinding.ItemTaskBinding
import com.example.lesson_mvvm.model.Task


class TaskAdapter(
    private val onLongClickListenerTask: (Task) -> Unit,
    private val isTaskChecked: (Task, Boolean) -> Unit,
    private val onClickTask: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var list = mutableListOf<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTask(list: MutableList<Task>) {
        this.list = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteTask(task: Task) {
        list.remove(task)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Task) = with(binding) {
            title.text = task.title
            description.text = task.description
            checkBox.isChecked = task.checkBox
            itemView.setOnLongClickListener {
                onLongClickListenerTask(task)
                true
            }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                isTaskChecked(task, isChecked)
                Log.e("ololo", "onBind:$task \t $isChecked ")
            }
            itemView.setOnClickListener {
                onClickTask(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size
}
