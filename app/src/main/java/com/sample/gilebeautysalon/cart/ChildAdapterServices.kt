package com.sample.gilebeautysalon.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.ChildItemServicesBinding
import com.sample.gilebeautysalon.models.Data

class ChildAdapterServices(private val data : List<Data>) : RecyclerView.Adapter<ChildViewHolderServices>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolderServices {
        val binding = ChildItemServicesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChildViewHolderServices(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolderServices, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}