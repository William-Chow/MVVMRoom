package com.example.apiroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") val id: Int,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String,
    @ColumnInfo(name = "email") @SerializedName("email") val email: String,
    @ColumnInfo(name = "username") @SerializedName("username") val username: String,
    @ColumnInfo(name = "phone") @SerializedName("phone") val phone: String,
    @ColumnInfo(name = "website") @SerializedName("website") val website: String,
) : Serializable


//"address": {
//    "street": "Kulas Light",
//    "suite": "Apt. 556",
//    "city": "Gwenborough",
//    "zipcode": "92998-3874",
//    "geo": {
//        "lat": "-37.3159",
//        "lng": "81.1496"
//    }
//},
//"company": {
//    "name": "Romaguera-Crona",
//    "catchPhrase": "Multi-layered client-server neural-net",
//    "bs": "harness real-time e-markets"
//}