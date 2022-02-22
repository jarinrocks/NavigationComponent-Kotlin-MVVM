package com.sample.gilebeautysalon.listeners

import com.sample.gilebeautysalon.models.Data
import com.sample.gilebeautysalon.models.EmployeeDetail


/**
 * Created by AhmedEltaher
 */

interface RecyclerItemListener {
    fun onItemSelected(data: Data)
    fun onEmployeeSelected(employeeDetail: EmployeeDetail)
}
