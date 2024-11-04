package com.example.catlogic.api

import com.google.gson.annotations.SerializedName

data class EatingActivity(
    @SerializedName("date")
    val date: String,
    @SerializedName("food_brand")
    val foodBrand: String,
    @SerializedName("food_flavor")
    val foodFlavor: String,
    @SerializedName("quantity")
    val quantity: Int
)

data class PlayingActivity(
    @SerializedName("date")
    val date: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("activity_type")
    val activityType: String
)

data class HealthActivity(
    @SerializedName("date")
    val date: String,
    @SerializedName("weight")
    val weight: Float,
    @SerializedName("notes")
    val notes: String
)
