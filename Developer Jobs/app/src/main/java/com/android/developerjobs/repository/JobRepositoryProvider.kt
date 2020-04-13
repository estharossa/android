package com.android.developerjobs.repository

import com.android.developerjobs.api.JobApi

object JobRepositoryProvider {
    fun provideJobRepository():JobRepository{
        return JobRepository(jobApi = JobApi.create())
    }
}