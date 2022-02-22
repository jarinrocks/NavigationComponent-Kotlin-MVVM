package com.sample.gilebeautysalon.services

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.gilebeautysalon.api.Repository
import com.sample.gilebeautysalon.api.RetrofitClient
import com.sample.gilebeautysalon.models.Data
import com.sample.gilebeautysalon.models.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesViewModel() : ViewModel() {

    private val _data =  MutableLiveData<List<Data>>()
    val data : LiveData<List<Data>> get() = _data

    private val openDetailsPrivate = MutableLiveData<Data>()
    val openDetails : LiveData<Data> get() = openDetailsPrivate

    private val loadingProgressPrivate = MutableLiveData<Boolean>()
    val loadingProgress : LiveData<Boolean> get() = loadingProgressPrivate

    private val errorMessagePrivate = MutableLiveData<String>()
    val errorMessage : LiveData<String> get() = errorMessagePrivate

    init {

        loadingProgressPrivate.value = true
        val call = RetrofitClient.getInstance()?.apiService?.getServices()

        call?.enqueue(object : Callback<Services> {
            override fun onResponse(
                call: Call<Services>,
                response: Response<Services>
            ) {
                if (response.code() == 200) {
                    if (response.body()?.responseCode == 200){
                        _data.value = response.body()?.data
                        loadingProgressPrivate.value = false
                    }
                }
            }

            override fun onFailure(call: Call<Services>, t: Throwable) {
                //show error message
                loadingProgressPrivate.value = false
                if(t.message?.contains("No address associated with hostname") == true){
                    errorMessagePrivate.value = "No internet Connection"
                }else{
                    errorMessagePrivate.value = t.message
                }
            }
        })

    }

    fun openDetails(data: Data){
        openDetailsPrivate.value = data
    }
}