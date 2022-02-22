package com.sample.gilebeautysalon.api

import com.sample.gilebeautysalon.services.ServicesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class Repository {

    suspend fun getServices() : Flow<ServicesResponse> {
        return flow {
            ServicesResponse.LoadingProgress(true)
            val response = RetrofitClient.getInstance()?.apiService?.getServices()

            emit(
                try {
                    ServicesResponse.Services(listOf())
                    ServicesResponse.LoadingProgress(false)
                }catch (e: IOException){
                    ServicesResponse.ErrorMessage("No internet error")
                    ServicesResponse.LoadingProgress(false)
                }
            )
        }

    }

    /*fun getEmployees : Flow<> (

    )*/
}