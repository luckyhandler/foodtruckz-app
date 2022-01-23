package de.handler.foodtruckz.data

/**
 *  The model this app works with.
 *
 *  The model is the data representation which the UI needs to display.
 */
data class Foodtruck(
    val name: String,
    val description: String,
    val location: String,
    val url: String,
    val time: String,
    val logo: String?,
)
