package com.example.tenantmanagementsystemgroupa.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tenants")
data class Tenant(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String,

    @SerializedName("room_number")
    val roomNumber: String,

    @SerializedName("rent_amount")
    val rentAmount: Double,

    @SerializedName("is_paid")
    val isPaid: Boolean
)