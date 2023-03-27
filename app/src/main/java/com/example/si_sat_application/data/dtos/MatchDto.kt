package com.example.si_sat_application.data.dtos

import com.google.gson.annotations.SerializedName

data class MatchDto(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Daynight")
    val dayNight: String,
    @SerializedName("Id")
    val id: String,
    @SerializedName("League")
    val league: String,
    @SerializedName("Livecoverage")
    val liveCoverage: String,
    @SerializedName("Number")
    val number: String,
    @SerializedName("Offset")
    val offset: String,
    @SerializedName("Time")
    val time: String,
    @SerializedName("Type")
    val type: String
)