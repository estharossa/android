package com.android.midtermproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetail : AppCompatActivity() {

    private var id:String? = null
    private var title:String? = null
    private var description:String? = null
    private var status:String? = null
    private var category:String? = null
    private var durations:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        description = intent.getStringExtra("description")
        status = intent.getStringExtra("status")
        category = intent.getStringExtra("category")
        durations = intent.getStringExtra("durations")
        idTextView.text = "ID: $id"
        titleTextView.text = "Title: $title"
        descriptionTextView.text = "Description: $description"
        statusTextView.text = "Status: $status"
        categoryTextView.text = "Category: $category"
        durationsTextView.text = "Durations: $durations minutes"
    }
}
