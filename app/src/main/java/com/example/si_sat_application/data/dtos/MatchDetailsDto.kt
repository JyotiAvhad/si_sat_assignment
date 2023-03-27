package com.example.si_sat_application.data.dtos

import com.example.si_sat_application.data.dtos.MatchDto
import com.example.si_sat_application.data.dtos.OfficialsDto
import com.example.si_sat_application.data.dtos.SeriesDto
import com.example.si_sat_application.data.dtos.VenueDto
import com.google.gson.annotations.SerializedName

data class MatchDetailsDto(
    @SerializedName("Equation")
    val equation: String,
    @SerializedName("Match")
    val match: MatchDto,
    @SerializedName("Officials")
    val officials: OfficialsDto,
    @SerializedName("Player_Match")
    val playerMatch: String,
    @SerializedName("Result")
    val result: String,
    @SerializedName("Series")
    val series: SeriesDto,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Status_Id")
    val statusId: String,
    @SerializedName("Team_Away")
    val teamAway: String,
    @SerializedName("Team_Home")
    val teamHome: String,
    @SerializedName("Tosswonby")
    val tossWonBy: String,
    @SerializedName("Venue")
    val venue: VenueDto,
    @SerializedName("Weather")
    val weather: String,
    @SerializedName("Winmargin")
    val winMargin: String,
    @SerializedName("Winningteam")
    val winningTeam: String
)