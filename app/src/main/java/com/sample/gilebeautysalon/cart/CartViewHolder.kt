package com.sample.gilebeautysalon.cart

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.CartItemBinding
import com.sample.gilebeautysalon.models.Cart

class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cart: Cart) {

        val layoutManagerHorizontal = LinearLayoutManager(binding.rvEmployees.context,
            LinearLayoutManager.HORIZONTAL,false)

        binding.rvEmployees.layoutManager = layoutManagerHorizontal
        binding.rvEmployees.setHasFixedSize(true)
        binding.rvEmployees.adapter = ChildAdapter(cart.employees!!)

        val layoutManagerVertical = LinearLayoutManager(binding.rvEmployees.context,
            LinearLayoutManager.VERTICAL,false)

        binding.rvServices.apply {
            layoutManager = layoutManagerVertical
            setHasFixedSize(true)
            adapter = ChildAdapterServices(cart.services)
        }

    }

}