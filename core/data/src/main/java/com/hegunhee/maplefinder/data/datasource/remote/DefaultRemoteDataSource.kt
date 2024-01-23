package com.hegunhee.maplefinder.data.datasource.remote

import com.hegunhee.maplefinder.data.api.MapleApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val mapleOcidApi: MapleOcidApi,
    private val mapleApi: MapleApi
) : RemoteDataSource {
}