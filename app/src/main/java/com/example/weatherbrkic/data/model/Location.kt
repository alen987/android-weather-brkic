package com.example.weatherbrkic.data.model

data class Location(
    val Version:Int,
    val Key: String,
    val Type:String,
    val Rank:Int,
    val LocalizedName: String,
    val Country: Country,
    val AdministrativeArea: AdministrativeArea
)

