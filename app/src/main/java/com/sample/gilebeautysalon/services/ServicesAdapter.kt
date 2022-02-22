package com.sample.gilebeautysalon.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.ServiceItemBinding
import com.sample.gilebeautysalon.listeners.RecyclerItemListener
import com.sample.gilebeautysalon.models.Data
import com.sample.gilebeautysalon.models.EmployeeDetail

class ServicesAdapter : RecyclerView.Adapter<ServicesViewHolder>() {

    private var data = mutableListOf<Data>()
    private lateinit var servicesViewModel: ServicesViewModel

    fun addServicesList(data: List<Data>, viewModel: ServicesViewModel){
        this.data = data.toMutableList()
        servicesViewModel = viewModel
        notifyDataSetChanged()
    }
    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(data: Data) {
            servicesViewModel.openDetails(data)
        }

        override fun onEmployeeSelected(employeeDetail: EmployeeDetail) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val itemBinding = ServiceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServicesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.bind(data[position],onItemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}