package com.android.recyclerviewcontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact_details.*

class ContactDetails : AppCompatActivity() {
    private var firstName:String? = null
    private var lastName:String? = null
    private var phoneNumber:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        firstName = intent.getStringExtra("firstName")
        lastName = intent.getStringExtra("lastName")
        phoneNumber = intent.getStringExtra("phoneNumber")
        firstNameTextView.text = "First name: $firstName"
        lastNameTextView.text = "Last name: $lastName"
        phoneNumberTextView.text = "Phone number: $phoneNumber"
    }
}
