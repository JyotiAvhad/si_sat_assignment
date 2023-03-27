package com.example.si_sat_application.data.dtos

import com.example.si_sat_application.domain.models.Venue
import com.google.gson.annotations.SerializedName

data class VenueDto(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
) {
    fun toVenue(): Venue {
        return Venue(
            id = id,
            name = name
        )
    }
}