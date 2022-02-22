package com.sample.gilebeautysalon.models

data class Services(val responseCode: Int, val data: List<Data>)
data class Data(val id:Int, val name:String, val price:Int, val image:String)

data class Employees(val responseCode: Int, val data: List<EmployeeDetail>)
data class EmployeeDetail(val id:Int, val name:String, val image:String)

data class EmployeeAdded(val id:Int, val name:String, val image:String,val isAdded: Boolean)


data class AddedServices(val services: Data, val employees: List<EmployeeDetail>?)

data class Cart(val services: List<Data>, val employees: List<EmployeeDetail>?)
