package com.android.midtermproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.android.midtermproject.model.ToDoItem
import com.android.midtermproject.view.AddToDoFragment
import com.android.midtermproject.view.FirstFragment
import com.android.midtermproject.view.ToDoDetailsFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.addtodo_fragment.*

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment = FirstFragment()
        fragmentManager
            .beginTransaction()
            .replace(R.id.toDoListLayout, firstFragment)
            .commit()
    }
}
