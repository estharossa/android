package com.android.developerjobs.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.developerjobs.R
import com.android.developerjobs.adapter.Adapter
import com.android.developerjobs.model.Job
import com.android.developerjobs.repository.JobRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_job_list.*

class JobListFragment : Fragment() {

    private var jobAdapter: Adapter? = null
    private var jobsList: MutableList<Job> = mutableListOf()
    companion object {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = JobRepositoryProvider.provideJobRepository()
        jobAdapter = Adapter(
            jobClickListener = {
                detailedInfo(this.jobsList[it])
            }
        )
        val manager = LinearLayoutManager(context)
        jobsRecyclerView.apply {
            layoutManager = manager
            adapter = jobAdapter
        }
        repository.getJobs()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("Jobs", it.size.toString())
                    jobAdapter?.addItems(it)
                    this.jobsList.addAll(it)
                },
                {
                    Log.d("Error", it.message)
                }
            )
    }

    private fun detailedInfo(job: Job){
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.mainActivity, DetailedInfo.create(job))
            ?.apply { addToBackStack(this::class.java.simpleName) }
            ?.commit()
    }
}
