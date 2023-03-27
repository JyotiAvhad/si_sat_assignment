package com.example.si_sat_application.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.si_sat_application.domain.repositories.MatchRepository
import com.example.si_sat_application.ui.utils.Resource
import com.rahulghag.siassignment.domain.models.MatchDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MatchViewModel constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MatchListUiState())
    val uiState: StateFlow<MatchListUiState> = _uiState.asStateFlow()

    lateinit var selectedMatchId: String

    init {
        getMatchDetails()
    }

    private fun getMatchDetails() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val firstMatchDetailsDeferred =
            async { matchRepository.getMatchDetails(matchId = "nzin01312019187360") }
        val secondMatchDetailsDeferred =
            async { matchRepository.getMatchDetails(matchId = "sapk01222019186652") }

        val firstMatchDetails = firstMatchDetailsDeferred.await()
        val secondMatchDetails = secondMatchDetailsDeferred.await()

        val results = listOf(firstMatchDetails, secondMatchDetails)
        results.forEach { result ->
            if (result is Resource.Error) {
                _uiState.update {
                    it.copy(
                        message = result.message,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        matchDetails = listOf(firstMatchDetails.data, secondMatchDetails.data),
                        isLoading = false,
                        message = null
                    )
                }
            }
        }
    }

    fun messageShown() {
        _uiState.update {
            it.copy(message = null)
        }
    }

    fun updateSelectedMatchId(matchId: String) {
        selectedMatchId = matchId
    }

    fun getMatchDetailsById(): MatchDetails? {
        return _uiState.value.matchDetails.first {
            selectedMatchId == it?.series?.id
        }
    }
}