package com.example.si_sat_application.data

import com.example.si_sat_application.data.dtos.TeamDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchApi {
    @GET("{teamId}.json")
    suspend fun getMatchDetails(
        @Path("teamId") matchId: String
    ): Response<TeamDetailsResponse>

    companion object {
        const val BASE_URL = "https://demo.sportz.io/"
    }
}