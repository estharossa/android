package com.android.midtermproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.midtermproject.AppDatabase
import com.android.midtermproject.R
import com.android.midtermproject.ToDoItemDAO
import com.android.midtermproject.adapter.Adapter
import com.android.midtermproject.model.ToDoItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment(): Fragment() {

    private var toDoAdapter: Adapter? = null
    private var toDoList: MutableList<ToDoItem> = mutableListOf()
    private var db: AppDatabase? = null
    private var toDoItemDao: ToDoItemDAO? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.accessDB()

        toDoAdapter = Adapter(
            itemClickListener = {
                goToDetailsFragment(toDoAdapter?.getItem(it)!!)
            }
        )
        val manager = LinearLayoutManager(context)
        toDoRecyclerView.apply {
            layoutManager = manager
            adapter = toDoAdapter
        }
        Log.d("todolist", this.toDoList.toString())


        addButton.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.toDoListLayout, AddToDoFragment.create())
                ?.commit()
        }

    }

    private fun goToDetailsFragment(todo: ToDoItem){
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.toDoListLayout, ToDoDetailsFragment.create(todo))
            ?.commit()
    }

    private fun accessDB(){
        Observable.fromCallable {
            db = context?.let { AppDatabase.getAppDataBase(context = context!!) }
            toDoItemDao = db?.toDoItemDAO()
            db?.toDoItemDAO()?.getGenders()
        }.doOnNext {
            Log.d("db list", it.toString())
            if (it != null) {
                toDoAdapter?.addItems(it)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}