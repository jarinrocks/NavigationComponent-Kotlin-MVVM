package com.sample.gilebeautysalon.services

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.gilebeautysalon.R
import com.sample.gilebeautysalon.databinding.ServicesFragmentBinding
import com.sample.gilebeautysalon.models.Data

class ServicesFragment : Fragment() {

    companion object {
        fun newInstance() = ServicesFragment()
    }

    private val viewModel: ServicesViewModel by viewModels()

    private val servicesAdapter = ServicesAdapter()

    private var _binding: ServicesFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ServicesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvServices.layoutManager = layoutManager
        binding.rvServices.setHasFixedSize(true)
        binding.rvServices.adapter = servicesAdapter

        observeViewModels()
    }

    private fun observeViewModels() {
        viewModel.data.observe(requireActivity(), servicesObserver)
        viewModel.openDetails.observe(requireActivity(),openDetailsObserver)
        viewModel.loadingProgress.observe(requireActivity(),loadingObserver)
        viewModel.errorMessage.observe(requireActivity(),errorMessageObserver)
    }

    // Create the observer which updates the UI.
    private val servicesObserver = Observer<List<Data>> {
        binding.tvErrorMessage.visibility = View.GONE
        servicesAdapter.addServicesList(it,viewModel)
    }

    private val openDetailsObserver = Observer<Data> {
        val bundle = bundleOf("name" to it.name , "price" to it.price, "imagePath" to it.image)
        findNavController().navigate(R.id.action_servicesFragment_to_employeeFragment,bundle)
    }

    private val loadingObserver = Observer<Boolean> {
        if (it)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

    private val errorMessageObserver = Observer<String> {
        binding.tvErrorMessage.visibility = View.VISIBLE
        binding.tvErrorMessage.text = it
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}