package com.sample.gilebeautysalon.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.gilebeautysalon.models.AddedServices

class CartViewModel : ViewModel() {

    private val addedServices = mutableListOf<AddedServices>()
    private val addedServicesLiveDataPrivate = MutableLiveData<List<AddedServices>>()
    val addedServicesLiveData : LiveData<List<AddedServices>> get() = addedServicesLiveDataPrivate

    fun addServices(addedService: AddedServices){
        for ((index,service) in addedServices.withIndex()){
            if (service.services.name == addedService.services.name){
                addedServices[index] = addedService
                addedServicesLiveDataPrivate.value = addedServices
                return
            }
        }
        addedServices.add(addedService)
        addedServicesLiveDataPrivate.value = addedServices
    }
}