package com.sample.gilebeautysalon.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.gilebeautysalon.R
import com.sample.gilebeautysalon.cart.CartViewModel
import com.sample.gilebeautysalon.databinding.EmployeeFragmentBinding
import com.sample.gilebeautysalon.models.AddedServices
import com.sample.gilebeautysalon.models.Data
import com.sample.gilebeautysalon.models.EmployeeAdded
import com.sample.gilebeautysalon.models.EmployeeDetail
import com.squareup.picasso.Picasso

class EmployeeFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeeFragment()
    }

    private val viewModel: EmployeeViewModel by viewModels()
    //private val cartViewModel: CartViewModel by viewModels()

    private val employeeAdapter = EmployeeAdapter()

    private var _binding: EmployeeFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView.

    private val binding get() = _binding!!

    private lateinit var serviceName : String
    private var price: Int = 0
    private lateinit var  imagePath: String

    private var addedEmployees = listOf<EmployeeDetail>()
    private var employees = listOf<EmployeeDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EmployeeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.arguments?.apply {
            serviceName = this.getString("name")!!
            price = this.getInt("price")
            imagePath = this.getString("imagePath")!!
        }


        binding.tvName.text = serviceName
        binding.tvPrice.text = "$"+price.toDouble().toString()
        Picasso.get().load(imagePath).fit()/*.placeholder(R.mipmap.ic_launcher_round)*/.into(binding.imageView)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmployees.layoutManager = layoutManager
        binding.rvEmployees.setHasFixedSize(true)
        binding.rvEmployees.adapter = employeeAdapter

        val model = ViewModelProvider(requireActivity())[CartViewModel::class.java]

        binding.btnAddToCart.setOnClickListener {
            model.addServices(
                AddedServices(
                services = Data(id = 0, name = serviceName, price = price, image = imagePath),
                employees = viewModel.addedServices.value
            )
            )
        }

        model.addedServicesLiveData.observe(viewLifecycleOwner,{
            for (service in it){
                if(service.services.name == serviceName){
                    addedEmployees = service.employees!!
                    for (employee in service.employees){
                        viewModel.addEmployee(employee)
                    }
                }
            }
            if (employees.isNotEmpty()){
                val employeesList = mutableListOf<EmployeeAdded>()
                for (employee in employees){
                    if (addedEmployees.contains(employee)){
                        employeesList.add(EmployeeAdded(employee.id,employee.name,employee.image,true))
                    }else{
                        employeesList.add(EmployeeAdded(employee.id,employee.name,employee.image,false))
                    }
                }
                employeeAdapter.addEmployees(employeesList,viewModel)
            }
        })


        binding.navBack.setOnClickListener {
            findNavController().popBackStack()
        }

        observeViewModels()
    }

    private fun observeViewModels() {
        viewModel.employeeDetailLiveData.observe(requireActivity(),employeeDetailObserver)
        viewModel.addedServices.observe(requireActivity(),addedServicesObserver)
        viewModel.loadingProgress.observe(requireActivity(),loadingObserver)
        viewModel.errorMessage.observe(requireActivity(),errorMessageObserver)
    }

    private val employeeDetailObserver = Observer<List<EmployeeDetail>> {
        employees = it
        val employeesList = mutableListOf<EmployeeAdded>()
        for (employee in it){
            if (addedEmployees.contains(employee)){
                employeesList.add(EmployeeAdded(employee.id,employee.name,employee.image,true))
            }else{
                employeesList.add(EmployeeAdded(employee.id,employee.name,employee.image,false))
            }
        }
        employeeAdapter.addEmployees(employeesList,viewModel)
    }

    private val addedServicesObserver = Observer<List<EmployeeDetail>> {
        if(it.isEmpty()){
            binding.btnAddToCart.setBackgroundResource(R.drawable.button_bg_unselected)
            binding.btnAddToCart.isEnabled = false
            binding.navBack.visibility = View.GONE
        }else{
            binding.btnAddToCart.setBackgroundResource(R.drawable.button_bg_selected)
            binding.btnAddToCart.isEnabled = true
            binding.navBack.visibility = View.VISIBLE
        }
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

}