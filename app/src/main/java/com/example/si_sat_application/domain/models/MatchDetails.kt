package com.rahulghag.siassignment.domain.models

import com.example.si_sat_application.domain.models.Officials
import com.example.si_sat_application.domain.models.Series
import com.example.si_sat_application.domain.models.Team
import com.example.si_sat_application.domain.models.Venue

data class MatchDetails(
    val officials: Officials,
    val nuggets: List<String>,
    val result: String,
    val series: Series,
    val teams: List<Team>,
    val venue: Venue,
    val weather: String,
){
    val teamNames get() = "${teams[0].name} vs ${teams[1].name}"
}