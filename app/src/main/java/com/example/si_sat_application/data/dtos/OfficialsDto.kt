package com.example.si_sat_application.data.dtos

import com.example.si_sat_application.domain.models.Officials
import com.google.gson.annotations.SerializedName

data class OfficialsDto(
    @SerializedName("Referee")
    val referee: String,
    @SerializedName("Umpires")
    val umpires: String
) {
    fun toOfficials(): Officials {
        return Officials(
            referee = referee,
            umpires = umpires
        )
    }
}