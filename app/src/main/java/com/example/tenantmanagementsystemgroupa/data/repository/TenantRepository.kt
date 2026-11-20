package com.example.tenantmanagementsystemgroupa.data.repository

import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import com.example.tenantmanagementsystemgroupa.data.remote.RetrofitClient

object TenantRepository {

    val tenants = mutableListOf<Tenant>()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun totalRent(): Double {
        return tenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }

    // Fetch all tenants from the remote API and sync the local list
    suspend fun fetchTenantsFromApi(): Boolean {
        return try {
            val response = RetrofitClient.apiService.getAllTenants()
            if (response.isSuccessful) {
                response.body()?.let { remoteTenants ->
                    tenants.clear()
                    tenants.addAll(remoteTenants)
                }
                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // Send a new tenant to the remote API and add it locally
    suspend fun postTenantToApi(tenant: Tenant): Boolean {
        return try {
            val response = RetrofitClient.apiService.addTenant(tenant)
            if (response.isSuccessful) {
                val savedTenant = response.body() ?: tenant
                tenants.add(savedTenant)
                true
            } else {
                tenants.add(tenant)
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            tenants.add(tenant)
            false
        }
    }
}