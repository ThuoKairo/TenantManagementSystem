package com.example.tenantmanagementsystemgroupa.data.remote

import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import retrofit2.Response
import retrofit2.http.*

interface TenantApiService {

    @GET("tenants")
    suspend fun getAllTenants(): Response<List<Tenant>>

    @POST("tenants")
    suspend fun addTenant(@Body tenant: Tenant): Response<Tenant>

    @PUT("tenants/{id}")
    suspend fun updateTenant(
        @Path("id") id: String,
        @Body tenant: Tenant
    ): Response<Tenant>

    @DELETE("tenants/{id}")
    suspend fun deleteTenant(@Path("id") id: String): Response<Unit>
}