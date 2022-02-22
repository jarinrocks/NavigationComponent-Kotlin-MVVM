package com.sample.gilebeautysalon.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.gilebeautysalon.Utils
import com.sample.gilebeautysalon.databinding.CartFragmentBinding
import com.sample.gilebeautysalon.models.Cart
import com.sample.gilebeautysalon.models.Data

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    //private val viewModel: CartViewModel by viewModels()

    private var _binding: CartFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView.

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.layoutManager = layoutManager
        binding.rvCart.setHasFixedSize(true)

        val model = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        // observing the change in the message declared in SharedViewModel
        model.addedServicesLiveData.observe(viewLifecycleOwner, {
            // updating data in displayMsg

            val addedServices = it.toMutableList()
            val cart = mutableListOf<Cart>()

            for(service in addedServices){
                val ser = mutableListOf<Data>()
                for(s in addedServices){
                   if(Utils.sortAscending(s.employees!!) == Utils.sortAscending(service.employees!!)){
                       ser.add(s.services)
                   }
                }
                if(ser.isEmpty())
                    cart.add(Cart(listOf(service.services),service.employees))
                else{
                    var duplicate = false
                    for ((i,c) in cart.withIndex()){
                        if(Utils.sortAscendingServices(c.services) == Utils.sortAscendingServices(ser)){
                            cart[i] = Cart(ser,service.employees)
                            duplicate = true
                        }
                    }
                    if (!duplicate)
                        cart.add(Cart(ser,service.employees))
                }
            }
            if (cart.isEmpty()) {
                binding.tvCart.visibility = View.VISIBLE
                binding.rvCart.visibility = View.GONE
            }
            else {
                binding.tvCart.visibility = View.GONE
                binding.rvCart.visibility = View.VISIBLE
                binding.rvCart.adapter =  CartAdapter(cart.toList())
            }
            Log.e("Cart Group Count", cart.size.toString())
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}