package com.example.si_sat_application.domain.models

data class Player(
    val isKeeper: Boolean = false,
    val isCaptain: Boolean = false,
    val name: String
)