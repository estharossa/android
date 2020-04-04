package com.android.midtermproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.midtermproject.AppDatabase
import com.android.midtermproject.R
import com.android.midtermproject.ToDoItemDAO
import com.android.midtermproject.model.ToDoItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.addtodo_fragment.*
import kotlinx.android.synthetic.main.second_fragment.*
import kotlinx.android.synthetic.main.second_fragment.categoryEditText
import kotlinx.android.synthetic.main.second_fragment.descriptionEditText
import kotlinx.android.synthetic.main.second_fragment.durationsEditText
import kotlinx.android.synthetic.main.second_fragment.goBack
import kotlinx.android.synthetic.main.second_fragment.statusEditText
import kotlinx.android.synthetic.main.second_fragment.titleEditText

class ToDoDetailsFragment(private val todo: ToDoItem): Fragment() {

    private var db:AppDatabase? = null
    private var toDoItemDao: ToDoItemDAO? = null

    companion object{
        fun create(todo: ToDoItem) = ToDoDetailsFragment(todo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idTextView.text = todo.id.toString()
        titleEditText.setText(todo.title)
        descriptionEditText.setText(todo.description)
        statusEditText.setText(todo.status)
        categoryEditText.setText(todo.category)
        durationsEditText.setText(todo.durations)

        updateButton.setOnClickListener {
            Observable.fromCallable {
                db = AppDatabase.getAppDataBase(context = context!!)
                toDoItemDao = db?.toDoItemDAO()
                val todo = db?.toDoItemDAO()?.getItemById(idTextView.text.toString().toInt())
                todo?.title = titleEditText.text.toString()
                todo?.description = descriptionEditText.text.toString()
                todo?.status = statusEditText.text.toString()
                todo?.category = categoryEditText.text.toString()
                todo?.durations = durationsEditText.text.toString()
                db?.toDoItemDAO()?.updateToDo(todo!!)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }

        goBack.setOnClickListener {
            val firstFragment = FirstFragment()
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.toDoListLayout, firstFragment)
                ?.apply { }
                ?.commit()
        }
    }
}