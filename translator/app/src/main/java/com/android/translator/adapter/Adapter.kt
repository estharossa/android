package com.android.translator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.translator.R
import com.android.translator.model.Item

import kotlinx.android.synthetic.main.list_item.view.*

class Adapter(private val deleteClickListener: (pos:Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var toDoList: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(toDoList[position], deleteClickListener)
    }

    fun addItems(list: List<Item>) {
        toDoList.clear()
        toDoList.addAll(list)
        notifyDataSetChanged()
    }

    fun deleteWord(pos:Int){
        toDoList.removeAt(pos)
        notifyDataSetChanged()
    }

    fun getItem(id:Int):Item = toDoList[id]

    private class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

        private val savedTextView = itemView.savedTextView
        private val deleteButton = itemView.deleteButton

        fun bind(item: Item, deleteClickListener: (pos:Int) -> Unit) {
            savedTextView.text = item.text
            deleteButton.setOnClickListener {
                deleteClickListener(adapterPosition)
            }
        }

    }
}
