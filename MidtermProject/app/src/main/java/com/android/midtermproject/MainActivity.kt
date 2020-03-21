package com.android.midtermproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    I DON'T KNOW HOW TO WORK WITH FRAGMENTS PROPERLY, THAT'S WHY I USED AN ACTIVITY FOR DETAIL PAGE :)

    private val fragmentManager = supportFragmentManager
    private var idNew:Int? = null
    private var title:String? = null
    private var description:String? = null
    private var status:String? = null
    private var category:String? = null
    private var durations:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoList:MutableList<ToDoItem> = mutableListOf()
        var id = 0;
        toDoList.add(ToDoItem(id++,"Pass MidTerm","You have to create a to-do-list application", "DONE", "Urgent", "120"))
        toDoList.add(ToDoItem(id++,"Go to the gym","Today at 17-00 you have to go to the gym", "NOT DONE", "Urgent", "120"))
        toDoList.add(ToDoItem(id++,"Buy flowers for 8th of March","BUY FLOWERS", "DONE", "Urgent", "120"))
        toDoList.add(ToDoItem(id++,"Do homework","Do homework on CV, Android, Web", "DONE", "Urgent", "120"))
        toDoList.add(ToDoItem(id++,"Play","You have to create a to-do-list application", "DONE", "Urgent", "120"))
        toDoList.add(ToDoItem(id++,"Go to the night club","You have to create a to-do-list application", "DONE", "Urgent", "120"))
        toDoList.add(ToDoItem(id++,"Go to the movie","You have to create a to-do-list application", "DONE", "Urgent", "120"))


        val firstFragment = FirstFragment(toDoList)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.toDoListLayout, firstFragment)
        fragmentTransaction.commit()

        addButton.setOnClickListener {
            val intent = Intent(this, InputToDo::class.java)
            startActivity(intent)

        }
    }
}
