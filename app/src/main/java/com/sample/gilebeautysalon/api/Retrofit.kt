package com.sample.gilebeautysalon.api

import com.sample.gilebeautysalon.BuildConfig
import com.sample.gilebeautysalon.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient{

    private val retrofit: Retrofit
    val apiService: ApiService? get() = service

    companion object {
        private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        private var instance: RetrofitClient? = null
        private var service: ApiService? = null
        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()

        fun getInstance(): RetrofitClient? {
            if (instance == null) {
                instance = RetrofitClient()
            }
            return instance
        }

    }

    init {
        httpClient.interceptors().add(Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val builder: Request.Builder = originalRequest.newBuilder()
                .method(originalRequest.method, originalRequest.body)
            chain.proceed(builder.build())
        })

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            // add logging as last interceptor
            httpClient.addInterceptor(logging)
        }

        retrofit = Retrofit.Builder().client(httpClient.build()).baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)
    }
}