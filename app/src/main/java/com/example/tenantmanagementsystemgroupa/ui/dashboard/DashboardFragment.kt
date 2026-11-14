package com.example.tenantmanagementsystemgroupa.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystemgroupa.databinding.FragmentDashboardBinding
import com.example.tenantmanagementsystemgroupa.ui.viewmodel.TenantViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    // Shared ViewModel – the same instance is used by all three Fragments
    private val viewModel: TenantViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onCreateView")
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onViewCreated")

        // Observe LiveData from the ViewModel instead of reading the repository directly
        viewModel.tenantCountText.observe(viewLifecycleOwner) { text ->
            binding.tenantCountTextView.text = text
        }

        viewModel.totalRentText.observe(viewLifecycleOwner) { text ->
            binding.totalRentTextView.text = text
        }

        binding.addTenantButton.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToAddTenantFragment()
            findNavController().navigate(action)
        }

        binding.viewTenantsButton.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToViewTenantsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "   DashboardFragment: onDestroy")
    }
}