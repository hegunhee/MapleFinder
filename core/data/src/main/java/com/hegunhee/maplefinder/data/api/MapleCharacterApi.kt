package com.hegunhee.maplefinder.data.api

import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterAbilityResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterDojangResponse
import com.hegunhee.maplefinder.data.api.model.character.CharacterBasicResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterHyperStatResponse
import com.hegunhee.maplefinder.data.api.model.character.stat.CharacterStatResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MapleCharacterApi {

    @GET("dojang")
    suspend fun getCharacterDojang(
        @Query("ocid") ocid: String,
        @Query("date") date: String
    ): CharacterDojangResponse

    @GET("basic")
    suspend fun getCharacterBasic(
        @Query("ocid") ocid: String,
        @Query("date") date: String
    ): CharacterBasicResponse

    @GET("stat")
    suspend fun getCharacterStat(
        @Query("ocid") ocid: String,
        @Query("date") date: String
    ): CharacterStatResponse

    @GET("hyper-stat")
    suspend fun getCharacterHyperStat(
        @Query("ocid") ocid: String,
        @Query("date") date: String
    ): CharacterHyperStatResponse

    @GET("ability")
    suspend fun getCharacterAbility(
        @Query("ocid") ocid: String,
        @Query("date") date: String
    ) : CharacterAbilityResponse
}