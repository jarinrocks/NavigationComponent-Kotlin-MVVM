package com.sample.gilebeautysalon

import com.sample.gilebeautysalon.models.Data
import com.sample.gilebeautysalon.models.EmployeeDetail

object Utils {

    fun sortAscending(list: List<EmployeeDetail>): List<EmployeeDetail> {
        return list.sortedBy { it.name }
    }

    fun sortAscendingServices(list: List<Data>): List<Data> {
        return list.sortedBy { it.name }
    }

}
