package com.android.recyclerviewcontacts

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class Adapter constructor(private val contactClickListener: (position:Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var contactsList:MutableList<Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(contactsList[position], contactClickListener)

    }

    fun addItems(list: List<Contact>){
        contactsList.clear()
        contactsList.addAll(list)
        notifyDataSetChanged()
    }

    private class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)){

        private val contactNameTextView = itemView.contactNameTextView
        private val itemLayout = itemView.itemLayout

        fun bind(contact: Contact, contactClickListener: (position: Int) -> Unit){
            contactNameTextView.text = contact.getFirstName()
            itemLayout.setOnClickListener {
                contactClickListener(adapterPosition)
            }
        }

    }
}