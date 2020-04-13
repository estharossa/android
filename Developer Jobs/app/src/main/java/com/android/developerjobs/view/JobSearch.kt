package com.android.developerjobs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.developerjobs.R
import com.android.developerjobs.adapter.Adapter
import com.android.developerjobs.model.Job
import kotlinx.android.synthetic.main.fragment_job_list.*
import kotlinx.android.synthetic.main.fragment_job_list.jobsRecyclerView
import kotlinx.android.synthetic.main.fragment_job_search.*


class JobSearch(private val jobs: List<Job>) : Fragment() {

    private var jobAdapter: Adapter? = null

    companion object {
        fun create(jobs:List<Job>) = JobSearch(jobs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobAdapter = Adapter(
            jobClickListener = {
            }
        )
        val manager = LinearLayoutManager(context)
        jobsRecyclerView.apply {
            layoutManager = manager
            adapter = jobAdapter
        }
        jobAdapter?.addItems(jobs)
    }

}
