package com.android.recyclerviewcontacts

class Contact {
    private var firstName:String
    private var lastName:String
    private var phoneNumber:String

    constructor(firstName:String, lastName:String, phoneNumber:String){
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
    }

    fun getFirstName():String {
        return firstName
    }
    fun getLastName():String = lastName
    fun getPhoneNumber():String = phoneNumber
    fun getInfo():String = "$firstName $lastName"
}