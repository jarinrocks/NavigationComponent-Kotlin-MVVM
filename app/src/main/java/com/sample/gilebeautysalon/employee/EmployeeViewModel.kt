package com.sample.gilebeautysalon.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.gilebeautysalon.api.RetrofitClient
import com.sample.gilebeautysalon.models.EmployeeDetail
import com.sample.gilebeautysalon.models.Employees
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeViewModel : ViewModel() {

    private val employeesLiveDataPrivate = MutableLiveData<List<EmployeeDetail>>()
    val employeeDetailLiveData : LiveData<List<EmployeeDetail>> get() = employeesLiveDataPrivate

    private val addedServicesPrivate = MutableLiveData<List<EmployeeDetail>>()
    val addedServices : LiveData<List<EmployeeDetail>> get() = addedServicesPrivate

    private val addedEmployees = mutableListOf<EmployeeDetail>()

    private val loadingProgressPrivate = MutableLiveData<Boolean>()
    val loadingProgress : LiveData<Boolean> get() = loadingProgressPrivate

    private val errorMessagePrivate = MutableLiveData<String>()
    val errorMessage : LiveData<String> get() = errorMessagePrivate

    init {
        loadingProgressPrivate.value = true

        val call = RetrofitClient.getInstance()?.apiService?.getEmployees()

        call?.enqueue(object : Callback<Employees>{
            override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                if (response.code() == 200 && response.body()?.responseCode == 200){
                    employeesLiveDataPrivate.value = response.body()?.data
                    loadingProgressPrivate.value = false
                }
            }

            override fun onFailure(call: Call<Employees>, t: Throwable) {
                loadingProgressPrivate.value = false
                if(t.message?.contains("No address associated with hostname") == true){
                    errorMessagePrivate.value = "No internet Connection"
                }else{
                    errorMessagePrivate.value = t.message
                }
            }

        })
    }

    fun addEmployee(employeeDetail: EmployeeDetail) {
        if(addedEmployees.contains(employeeDetail)){
            return
        }
        addedEmployees.add(employeeDetail)
        addedServicesPrivate.value = addedEmployees
    }

    fun removeEmployee(employeeDetail : EmployeeDetail) {
        addedEmployees.remove(employeeDetail)
        addedServicesPrivate.value = addedEmployees
    }
}