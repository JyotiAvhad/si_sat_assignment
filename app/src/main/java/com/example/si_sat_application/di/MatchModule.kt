package com.example.si_sat_application.di

import com.example.si_sat_application.data.MatchApi
import com.example.si_sat_application.data.dtos.repository.MatchRepositoryImpl
import com.example.si_sat_application.domain.repositories.MatchRepository

object ProfileModule {
    fun provideMatchRepository(
        matchApi: MatchApi,
    ): MatchRepository {
        return MatchRepositoryImpl(matchApi = matchApi)
    }
}