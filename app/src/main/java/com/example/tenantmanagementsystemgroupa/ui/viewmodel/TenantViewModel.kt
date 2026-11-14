
package com.example.tenantmanagementsystemgroupa.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystemgroupa.Tenant
import com.example.tenantmanagementsystemgroupa.data.local.TenantDatabase
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import kotlinx.coroutines.launch

class TenantViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository: TenantRepository

    val tenants: LiveData<List<Tenant>>

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

    // Add a tenant to the database
    fun addTenant(
        name: String,
        phone: String,
        rent: String
    ): Tenant? {

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

        // Create the tenant
        val tenant = Tenant(
            cleanName,
            cleanPhone,
            cleanRent
        )

        // Save tenant using a coroutine
        viewModelScope.launch {
            repository.addTenant(tenant)
        }

        return tenant
    }

    init {
        // Lifecycle logging
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}
