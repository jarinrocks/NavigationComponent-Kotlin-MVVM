package com.sample.gilebeautysalon.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.CartItemBinding
import com.sample.gilebeautysalon.models.Cart

class CartAdapter(private val cart: List<Cart>) : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cart[position])
    }

    override fun getItemCount(): Int {
        return cart.size
    }

}