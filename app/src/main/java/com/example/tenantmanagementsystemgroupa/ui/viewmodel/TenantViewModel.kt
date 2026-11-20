package com.example.tenantmanagementsystemgroupa.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import kotlinx.coroutines.launch

class TenantViewModel : ViewModel() {

    private val _tenants = MutableLiveData<List<Tenant>>(TenantRepository.tenants.toList())
    val tenants: LiveData<List<Tenant>> = _tenants

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String> = _apiStatus

    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            list.joinToString("\n\n") { it.summary() }
        }
    }

    init {
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
        syncTenantsFromApi()
    }

    fun syncTenantsFromApi() {
        viewModelScope.launch {
            val success = TenantRepository.fetchTenantsFromApi()
            _tenants.value = TenantRepository.tenants.toList()
            _apiStatus.value = if (success) "Synced with API" else "Offline mode"
            Log.d("TENANT_API", "Sync result: $success, count=${TenantRepository.tenants.size}")
        }
    }

    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()

        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }

        val tenant = Tenant(cleanName, cleanPhone, cleanRent)

        viewModelScope.launch {
            val posted = TenantRepository.postTenantToApi(tenant)
            _tenants.value = TenantRepository.tenants.toList()
            _apiStatus.value = if (posted) "Saved to API" else "Saved locally (offline)"
            Log.d("TENANT_API", "POST tenant result: $posted")
        }

        return tenant
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}