package com.sample.gilebeautysalon.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.EmployeeItemBinding
import com.sample.gilebeautysalon.listeners.RecyclerItemListener
import com.sample.gilebeautysalon.models.Data
import com.sample.gilebeautysalon.models.EmployeeAdded
import com.sample.gilebeautysalon.models.EmployeeDetail

class EmployeeAdapter : RecyclerView.Adapter<EmployeeViewHolder>() {

    private var employeeDetail = mutableListOf<EmployeeAdded>()
    private lateinit var viewModel : EmployeeViewModel

    fun addEmployees(employeeDetail: MutableList<EmployeeAdded>, viewModel: EmployeeViewModel){
        this.employeeDetail = employeeDetail
        this.viewModel = viewModel
        notifyDataSetChanged()
    }

    private val onItemAddedListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(data: Data) {

        }

        override fun onEmployeeSelected(employeeDetail: EmployeeDetail) {
            viewModel.addEmployee(employeeDetail)
        }
    }

    private val onItemRemovedListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(data: Data) {

        }

        override fun onEmployeeSelected(employeeDetail: EmployeeDetail) {
            viewModel.removeEmployee(employeeDetail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemBinding = EmployeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employeeDetail[position],onItemAddedListener,onItemRemovedListener)
    }

    override fun getItemCount(): Int {
        return  employeeDetail.size
    }
}