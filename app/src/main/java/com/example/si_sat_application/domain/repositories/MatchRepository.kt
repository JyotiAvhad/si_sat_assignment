package com.example.si_sat_application.domain.repositories

import com.rahulghag.siassignment.domain.models.MatchDetails
import com.example.si_sat_application.ui.utils.Resource

interface MatchRepository {
    suspend fun getMatchDetails(matchId: String): Resource<MatchDetails>
}