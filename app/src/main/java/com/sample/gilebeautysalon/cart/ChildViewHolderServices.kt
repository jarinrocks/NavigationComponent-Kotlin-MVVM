package com.sample.gilebeautysalon.cart

import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.ChildItemServicesBinding
import com.sample.gilebeautysalon.models.Data

class ChildViewHolderServices(private val binding: ChildItemServicesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Data) {
        binding.tvName.text = data.name
        binding.tvPrice.text = "$"+data.price.toDouble().toString()
    }
}