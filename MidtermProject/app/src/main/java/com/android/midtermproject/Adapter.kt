package com.android.midtermproject

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class Adapter constructor(private val itemClickListener: (position:Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var toDoList: MutableList<ToDoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(toDoList[position], itemClickListener)

    }

    fun addItems(list: List<ToDoItem>) {
        toDoList.clear()
        toDoList.addAll(list)
        notifyDataSetChanged()
    }

    fun addElement(item:ToDoItem){
        toDoList.add(item)
        notifyDataSetChanged()
    }

    private class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

        private val titleTextView = itemView.titleTextView
        private val statusTextView = itemView.statusTextView
        private val categoryTextView = itemView.categoryTextView
        private val toDoItemLayout = itemView.toDoItemLayout

        fun bind(item: ToDoItem, itemClickListener: (position: Int) -> Unit) {
            titleTextView.text = item.getTitle()
            statusTextView.text = item.getStatus()
            categoryTextView.text = item.getCategory()
            toDoItemLayout.setOnClickListener {
                itemClickListener(adapterPosition)
            }
        }

    }
}
