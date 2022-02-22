package com.sample.gilebeautysalon.employee

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sample.gilebeautysalon.R
import com.sample.gilebeautysalon.databinding.EmployeeItemBinding
import com.sample.gilebeautysalon.listeners.RecyclerItemListener
import com.sample.gilebeautysalon.models.EmployeeAdded
import com.sample.gilebeautysalon.models.EmployeeDetail
import com.squareup.picasso.Picasso

class EmployeeViewHolder(private val itemBinding: EmployeeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(
        employeeDetail: EmployeeAdded,
        onItemAddedListener: RecyclerItemListener,
        onItemRemovedListener: RecyclerItemListener
    ) {
        Picasso.get().load(employeeDetail.image).fit().into(itemBinding.imageView)
        itemBinding.tvName.text = employeeDetail.name
        if (employeeDetail.isAdded){
            itemBinding.imgDone.visibility = View.VISIBLE
            itemBinding.rlEmployeeItem.setBackgroundResource(R.drawable.item_bg_selected)
        }else{
            itemBinding.imgDone.visibility = View.GONE
            itemBinding.rlEmployeeItem.setBackgroundResource(R.drawable.item_border)
        }
        itemBinding.rlEmployeeItem.setOnClickListener {
            if (itemBinding.imgDone.isVisible) {
                itemBinding.imgDone.visibility = View.GONE
                onItemRemovedListener.onEmployeeSelected(
                    EmployeeDetail(employeeDetail.id,employeeDetail.name,employeeDetail.image)
                )
                itemBinding.rlEmployeeItem.setBackgroundResource(R.drawable.item_border)
            }else {
                itemBinding.imgDone.visibility = View.VISIBLE
                onItemAddedListener.onEmployeeSelected(
                    EmployeeDetail(employeeDetail.id,employeeDetail.name,employeeDetail.image)
                )
                itemBinding.rlEmployeeItem.setBackgroundResource(R.drawable.item_bg_selected)
            }
        }
    }
}