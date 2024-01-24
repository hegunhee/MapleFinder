package com.hegunhee.maplefinder.data.datasource.remote

import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val mapleOcidApi: MapleOcidApi,
    private val mapleCharacterApi: MapleCharacterApi
) : RemoteDataSource {
}