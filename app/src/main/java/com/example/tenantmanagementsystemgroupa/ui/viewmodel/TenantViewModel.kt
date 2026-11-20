
package com.example.tenantmanagementsystemgroupa.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import kotlinx.coroutines.launch

class TenantViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository: TenantRepository

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String> = _apiStatus

    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val tenantCountText: LiveData<String>

    val totalRentText: LiveData<String>

    val allTenantsText: LiveData<String>

    init {
        // Get the DAO from the Room database
        val tenantDao =
            TenantDatabase.getDatabase(application).tenantDao()

        // Create the Repository
        repository = TenantRepository(tenantDao)

        // Get tenants from the Repository
        tenants = repository.tenants

        // Calculate the number of tenants
        tenantCountText = tenants.map { list ->
            "Tenants\n${list.size}"
        }

        // Calculate total rent
        totalRentText = tenants.map { list ->
            val total = list.sumOf {
                it.rent.toDoubleOrNull() ?: 0.0
            }

            "Total rent\nKSh %,.0f".format(total)
        }

        // Display all tenants
        allTenantsText = tenants.map { list ->
            if (list.isEmpty()) {
                "No tenants yet. Add one from the Dashboard."
            } else {
                list.joinToString("\n\n") {
                    it.summary()
                }
            }
        }

        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
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

        // Validate the entered information
        if (
            cleanName.isEmpty() ||
            cleanPhone.isEmpty() ||
            cleanRent.isEmpty()
        ) {
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