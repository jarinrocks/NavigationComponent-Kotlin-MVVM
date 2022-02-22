package com.sample.gilebeautysalon.services

import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.databinding.ServiceItemBinding
import com.sample.gilebeautysalon.listeners.RecyclerItemListener
import com.sample.gilebeautysalon.models.Data
import com.squareup.picasso.Picasso

class ServicesViewHolder(private var itemBinding: ServiceItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(data: Data, onItemClickListener: RecyclerItemListener) {
        Picasso.get().load(data.image).fit().into(itemBinding.imageView)
        itemBinding.tvName.text = data.name
        itemBinding.tvPrice.text = "$${data.price.toDouble()}"
        itemBinding.rlServiceItem.setOnClickListener { onItemClickListener.onItemSelected(data) }

    }

}