package com.example.si_sat_application.data.dtos.repository

import com.example.si_sat_application.R
import com.example.si_sat_application.domain.repositories.MatchRepository
import com.google.gson.Gson
import com.example.si_sat_application.data.MatchApi
import com.example.si_sat_application.domain.models.Player
import com.example.si_sat_application.domain.models.Team
import com.rahulghag.siassignment.domain.models.MatchDetails
import com.example.si_sat_application.ui.utils.Resource
import com.example.si_sat_application.ui.utils.UiMessage
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class MatchRepositoryImpl(
    private val matchApi: MatchApi
) : MatchRepository {
    override suspend fun getMatchDetails(matchId: String): Resource<MatchDetails> {
        return try {
            val response =
                matchApi.getMatchDetails(matchId = matchId)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Resource.Success(
                        data = responseBody.toMatchDetails()
                            .copy(teams = parseTeams(responseBody.teams))
                    )
                } else {
                    Resource.Error(message = UiMessage.StringResource(R.string.error_something_went_wrong))
                }
            } else {
                Resource.Error(message = UiMessage.StringResource(R.string.error_something_went_wrong))
            }
        } catch (e: IOException) {
            Resource.Error(message = UiMessage.StringResource(R.string.error_no_internet_connection))
        } catch (e: HttpException) {
            Resource.Error(message = UiMessage.StringResource(R.string.error_something_went_wrong))
        }
    }

    private fun parseTeams(teamsResponse: Any): MutableList<Team> {
        val teamsJSONObject = JSONObject(Gson().toJson(teamsResponse))
        val teamKeys: Iterator<String> = teamsJSONObject.keys()
        val teams = mutableListOf<Team>()
        while (teamKeys.hasNext()) {
            val teamKey = teamKeys.next()
            val teamName = teamsJSONObject.getJSONObject(teamKey).getString("Name_Full")
            val playersJSONObject = teamsJSONObject.getJSONObject(teamKey).getJSONObject("Players")
            val playerKeys: Iterator<String> = playersJSONObject.keys()
            val players = mutableListOf<Player>()
            while (playerKeys.hasNext()) {
                val playerKey = playerKeys.next()
                val player = Player(
                    isKeeper = playersJSONObject.getJSONObject(playerKey).optBoolean("Iskeeper"),
                    isCaptain = playersJSONObject.getJSONObject(playerKey).optBoolean("Iscaptain"),
                    name = playersJSONObject.getJSONObject(playerKey).getString("Name_Full")
                )
                players.add(player)
            }
            teams.add(Team(name = teamName, players = players))
        }
        return teams
    }

    companion object {
        private const val TAG = "MatchRepositoryImpl"
    }
}