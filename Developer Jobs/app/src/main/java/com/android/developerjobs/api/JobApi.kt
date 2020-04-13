package com.android.developerjobs.api

import com.android.developerjobs.model.Job
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JobApi {
    companion object Factory {
        fun create(): JobApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jobs.github.com/")
                .build()

            return retrofit.create(JobApi::class.java);
        }
    }

    @GET("positions.json")
    fun get(): Observable<List<Job>>
}