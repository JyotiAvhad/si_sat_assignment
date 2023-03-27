package com.example.si_sat_application.data.dtos

import com.google.gson.annotations.SerializedName
import com.rahulghag.siassignment.domain.models.MatchDetails

data class TeamDetailsResponse(
    @SerializedName("Nuggets")
    val nuggets: List<String>,
    @SerializedName("Matchdetail")
    val matchDetails: MatchDetailsDto,
    @SerializedName("Teams")
    val teams: Any
) {
    fun toMatchDetails(): MatchDetails {
        return MatchDetails(
            officials = matchDetails.officials.toOfficials(),
            nuggets = nuggets,
            result = matchDetails.result,
            series = matchDetails.series.toSeries(),
            teams = emptyList(),
            venue = matchDetails.venue.toVenue(),
            weather = matchDetails.weather
        )
    }
}