package com.sesac.planet.network.main.planet

import com.sesac.planet.data.model.PlanetDetailInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PlanetDetailAPI {
    //홈화면에 오늘의 성장계획 리스트를 가져오는 api
    @GET("/planets/detail/{planet_id}")
    suspend fun getPlanetDetailInfo(
        @Header("X-ACCESS-TOKEN") token: String,
        @Path("planet_id") planetId: Int
    ): Response<PlanetDetailInfoResponse>
}