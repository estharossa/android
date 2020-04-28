package com.android.translator.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.translator.R
import com.android.translator.adapter.Adapter
import com.android.translator.db.AppDatabase
import com.android.translator.db.ItemDAO
import com.android.translator.model.Item
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_favourites.*


class FavouritesFragment() : Fragment() {

    private var db: AppDatabase? = null
    private var itemDao: ItemDAO? = null
    private var itemAdapter: Adapter? = null
    private var list: List<Item> = listOf()

    companion object {
        fun create() = FavouritesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accessDB()

        itemAdapter = Adapter(deleteClickListener = {
            this.itemAdapter?.deleteWord(it)
            Observable.fromCallable {
                db = context?.let { AppDatabase.getAppDataBase(context!!) }
                itemDao = db?.itemDAO()
                with(itemDao){
                    this?.deleteItem(list[it])
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        })
        val manager = LinearLayoutManager(context)
        recyclerView.apply {
            layoutManager = manager
            adapter = itemAdapter
        }
    }

    private fun accessDB(){
        Observable.fromCallable {
            db = context?.let { AppDatabase.getAppDataBase(context!!) }
            itemDao = db?.itemDAO()
            this.list = db?.itemDAO()?.getItems()!!
            this.list
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    if (it != null) {
                        this.itemAdapter?.addItems(it)
                    }
                },
                {
                    Log.d("Error", it.message)
                }
            )
    }
}
