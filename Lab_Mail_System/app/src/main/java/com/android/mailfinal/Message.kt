package com.android.mailfinal

import java.io.FileDescriptor

class Message {
    private val title: String
    private val sender: String
    private val description: String

    constructor(title:String, sender:String, description:String){
        this.title = title
        this.sender = sender
        this.description = description
    }

    fun getTitle():String = title;
    fun getSender():String = sender;
    fun getDescription():String = description
}