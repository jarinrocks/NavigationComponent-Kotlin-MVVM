package com.sample.gilebeautysalon.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.ChildItemBinding
import com.sample.gilebeautysalon.models.EmployeeDetail

class ChildAdapter(private val employees: List<EmployeeDetail>) : RecyclerView.Adapter<ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = ChildItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(employees[position])
    }

    override fun getItemCount(): Int {
        return employees.size
    }
}