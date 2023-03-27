package com.example.si_sat_application.ui.view

import com.example.si_sat_application.ui.utils.UiMessage
import com.rahulghag.siassignment.domain.models.MatchDetails

data class MatchListUiState(
    val matchDetails: List<MatchDetails?> = emptyList(),
    val isLoading: Boolean = false,
    val message: UiMessage? = null
)