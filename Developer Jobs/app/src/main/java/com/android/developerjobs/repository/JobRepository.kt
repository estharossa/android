package com.android.developerjobs.repository

import com.android.developerjobs.api.JobApi
import com.android.developerjobs.model.Job
import io.reactivex.Observable

class JobRepository(private val jobApi: JobApi) {
    fun getJobs():Observable<List<Job>>{
        return jobApi.get()
    }
}