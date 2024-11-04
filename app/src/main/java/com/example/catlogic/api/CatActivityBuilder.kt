package com.example.catlogic.api

class CatActivityBuilder {
    private var date: String? = null
    private var foodBrand: String? = null
    private var foodFlavor: String? = null
    private var quantity: Int? = null
    private var duration: Int? = null
    private var activityType: String? = null
    private var weight: Float? = null
    private var notes: String? = null

    fun setDate(date: String) = apply { this.date = date }

    fun setEatingDetails(brand: String, flavor: String, quantity: Int) = apply {
        this.foodBrand = brand
        this.foodFlavor = flavor
        this.quantity = quantity
    }

    fun setPlayingDetails(duration: Int, type: String) = apply {
        this.duration = duration
        this.activityType = type
    }

    fun setHealthDetails(weight: Float, notes: String) = apply {
        this.weight = weight
        this.notes = notes
    }

    fun build(): Any {
        return when {
            foodBrand != null -> EatingActivity(date!!, foodBrand!!, foodFlavor!!, quantity!!)
            duration != null -> PlayingActivity(date!!, duration!!, activityType!!)
            weight != null -> HealthActivity(date!!, weight!!, notes!!)
            else -> throw IllegalArgumentException("Invalid activity details")
        }
    }
}
