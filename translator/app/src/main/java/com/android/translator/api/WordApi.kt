package com.android.translator.api

import com.android.translator.model.Language
import com.android.translator.model.Word
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val API_KEY = "trnsl.1.1.20200414T083617Z.36c239e510ad266c.3d59505a04e5783a3e580f89d31d83bfe27c5994"
private const val BASE_URL = "https://translate.yandex.net/"

interface WordApi {
    companion object Factory {
        fun create(): WordApi {
            val requestInterceptor = Interceptor{
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor it.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(WordApi::class.java);
        }
    }

    @GET("api/v1.5/tr.json/translate")
    fun get(@Query("lang")lang:String, @Query("text")text:String): Single<Word>

    @GET("api/v1.5/tr.json/detect")
    fun detect(@Query("text") text:String): Single<Language>
}