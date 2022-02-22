package com.sample.gilebeautysalon.services

import com.sample.gilebeautysalon.models.Data

sealed class ServicesResponse{
    data class Services(val data: List<Data>) : ServicesResponse()
    data class LoadingProgress(val loadingProgress: Boolean) : ServicesResponse()
    data class ErrorMessage(val message: String) : ServicesResponse()
}
