package com.android.developerjobs.view

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat

import com.android.developerjobs.R
import com.android.developerjobs.model.Job
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detailed_info.*


class DetailedInfo(private val job: Job) : Fragment() {

    companion object{
        fun create(job:Job) = DetailedInfo(job)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initJob()
    }

    private fun initJob(){
        Picasso.get().load(job.company_logo).into(jobImageView)
        createdAtTextView.text = job.created_at
        companyTextView.text = job.company
        titleTextView.text = job.title
        locationTextView.text = job.location
        typeTextView.text = job.type
        descriptionTextView.text = HtmlCompat.fromHtml(job.description, 0)
        applyTextView.text = HtmlCompat.fromHtml(job.how_to_apply, 0)
    }
}
