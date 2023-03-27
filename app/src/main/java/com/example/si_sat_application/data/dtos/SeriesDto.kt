package com.example.si_sat_application.data.dtos

import com.example.si_sat_application.domain.models.Series
import com.google.gson.annotations.SerializedName

data class SeriesDto(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Tour")
    val tour: String,
    @SerializedName("Tour_Name")
    val tourName: String
) {
    fun toSeries(): Series {
        return Series(
            id = id,
            name = name,
            status = status,
            tour = tour,
            tourName = tourName
        )
    }
}