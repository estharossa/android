package com.android.recyclerviewcontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var contactAdapter: Adapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupContactList()
    }

    private fun setupContactList(){
        val contacts:MutableList<Contact> = mutableListOf()
        contacts.add(Contact("Aslan", "Kossiyabek","+77018955591"))
        contacts.add(Contact("Daniyar", "Amangeldi", "+77087033500"))
        contacts.add(Contact("Abylai", "Bazarbayev", "+77777777777"))
        contacts.add(Contact("Aslan", "Kossiyabek","+77018955591"))
        contacts.add(Contact("Daniyar", "Amangeldi", "+77087033500"))
        contacts.add(Contact("Abylai", "Bazarbayev", "+77777777777"))
        contacts.add(Contact("Aslan", "Kossiyabek","+77018955591"))
        contacts.add(Contact("Daniyar", "Amangeldi", "+77087033500"))
        contacts.add(Contact("Abylai", "Bazarbayev", "+77777777777"))
        contactAdapter = Adapter(
            contactClickListener = {
                val intent = Intent(this, ContactDetails::class.java)
                intent.putExtra("firstName", contacts[it].getFirstName())
                intent.putExtra("lastName", contacts[it].getLastName())
                intent.putExtra("phoneNumber", contacts[it].getPhoneNumber())
                startActivity(intent)
            }
        )
        val manager = LinearLayoutManager(this)
        contactsRecyclerView.apply {
            layoutManager = manager
            adapter = contactAdapter
        }
        contactAdapter?.addItems(contacts)
    }
}
