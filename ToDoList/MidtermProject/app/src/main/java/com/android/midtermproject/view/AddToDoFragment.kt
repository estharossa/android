package com.android.midtermproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.midtermproject.AppDatabase
import com.android.midtermproject.R
import com.android.midtermproject.ToDoItemDAO
import com.android.midtermproject.model.ToDoItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.addtodo_fragment.*

class AddToDoFragment(): Fragment() {
    private var db:AppDatabase? = null
    private var toDoItemDao: ToDoItemDAO? = null

    companion object {
        fun create() = AddToDoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.addtodo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewItemButton.setOnClickListener {
            Observable.fromCallable {
                db = AppDatabase.getAppDataBase(context = context!!)
                toDoItemDao = db?.toDoItemDAO()
                var toDoItem = ToDoItem(0,
                    titleEditText.text.toString(),
                    descriptionEditText.text.toString(),
                    statusEditText.text.toString(),
                    categoryEditText.text.toString(),
                    durationsEditText.text.toString()
                )
                with(toDoItemDao){
                    this?.saveToDoItem(toDoItem)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

        }

        goBack.setOnClickListener {
            val firstFragment = FirstFragment()
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.toDoListLayout, firstFragment)
                ?.apply { addToBackStack(this::class.java.simpleName) }
                ?.commit()
        }
    }


}