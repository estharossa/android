package com.android.midtermproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var description: String,
    var status: String,
    var category: String,
    var durations: String
){


}