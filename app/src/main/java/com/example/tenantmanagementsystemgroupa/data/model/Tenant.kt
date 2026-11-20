package com.example.tenantmanagementsystemgroupa.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Tenant(
    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("rent")
    val rent: String
) : Serializable {

    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}