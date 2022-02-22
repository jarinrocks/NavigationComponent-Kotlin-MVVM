package com.sample.gilebeautysalon.api

import com.sample.gilebeautysalon.models.Employees
import com.sample.gilebeautysalon.models.Services
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("challenge-services/")
    fun getServices() : Call<Services>

    @GET("challenge-employees/")
    fun getEmployees() : Call<Employees>

}