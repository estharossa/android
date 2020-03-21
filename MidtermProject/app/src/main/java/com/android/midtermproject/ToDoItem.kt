package com.android.midtermproject

class ToDoItem {
    private val id: Int
    private val title: String
    private val description: String
    private val status: String
    private val category: String
    private val durations: String

    constructor(
        id: Int,
        title: String,
        description: String,
        status: String,
        category: String,
        durations: String
    ) {
        this.id = id
        this.title = title
        this.description = description
        this.status = status
        this.category = category
        this.durations = durations
    }

    fun getId():Int = id
    fun getTitle():String = title
    fun getDescription():String = description
    fun getStatus():String = status
    fun getCategory():String = category
    fun getDurations():String = durations


}