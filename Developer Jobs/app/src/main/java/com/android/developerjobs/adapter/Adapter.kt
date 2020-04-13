package com.android.developerjobs.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.developerjobs.R
import com.android.developerjobs.model.Job
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class Adapter constructor(private val jobClickListener: (position: Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var jobList:MutableList<Job> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(jobList[position], jobClickListener)

    }

    fun addItems(list: List<Job>){
        jobList.clear()
        jobList.addAll(list)
        notifyDataSetChanged()
    }

    private class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)){

        private val jobImage = itemView.jobImageView
        private val title = itemView.titleTextView
        private val type = itemView.typeTextView
        private val created_at = itemView.createdAtTextView
        private val likeButton = itemView.likeButton
        private val jobLayout = itemView.jobInfoLayout

        fun bind(job:Job, jobClickListener: (position: Int) -> Unit){
            title.text = job.title
            type.text = job.type
            created_at.text = job.created_at
            Picasso.get().load(job.company_logo).into(jobImage);
            jobLayout.setOnClickListener {
                jobClickListener(adapterPosition)
            }
        }

    }
}