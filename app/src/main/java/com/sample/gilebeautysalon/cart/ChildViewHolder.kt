package com.sample.gilebeautysalon.cart

import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.ChildItemBinding
import com.sample.gilebeautysalon.models.EmployeeDetail
import com.squareup.picasso.Picasso

class ChildViewHolder(private val binding:  ChildItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(employeeDetail: EmployeeDetail) {
        Picasso.get().load(employeeDetail.image).fit().into(binding.empImage)
    }
}