package com.android.midtermproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment(list:MutableList<ToDoItem>): Fragment() {

    private var toDoAdapter: Adapter? = null
    private var toDoList = list
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toDoAdapter = Adapter(
            itemClickListener = {
                val intent = Intent(context, ItemDetail::class.java)
                intent.putExtra("id", toDoList[it].getId().toString())
                intent.putExtra("title", toDoList[it].getTitle())
                intent.putExtra("description", toDoList[it].getDescription())
                intent.putExtra("status", toDoList[it].getStatus())
                intent.putExtra("category", toDoList[it].getCategory())
                intent.putExtra("durations", toDoList[it].getDurations())
                startActivity(intent)
            }
        )
        val manager = LinearLayoutManager(context)
        toDoRecyclerView.apply {
            layoutManager = manager
            adapter = toDoAdapter
        }
        toDoAdapter?.addItems(toDoList)

    }

    fun addItem(item:ToDoItem){
        toDoAdapter?.addElement(item)
    }
}