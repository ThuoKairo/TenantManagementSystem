package com.example.tenantmanagementsystemgroupa.data.repository

import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import com.example.tenantmanagementsystemgroupa.data.remote.RetrofitClient

class TenantRepository(
    private val tenantDao: TenantDao
) {

    // Get all tenants from the Room database
    val tenants: LiveData<List<Tenant>> =
        tenantDao.getAllTenants()

    // Save a tenant in the Room database
    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
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