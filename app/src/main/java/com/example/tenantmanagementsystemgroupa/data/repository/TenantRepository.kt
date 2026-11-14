package com.example.tenantmanagementsystemgroupa.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsystemgroupa.Tenant
import com.example.tenantmanagementsystemgroupa.data.local.TenantDao

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
}
