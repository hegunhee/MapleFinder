package com.hegunhee.maplefinder.data.repository

import com.hegunhee.maplefinder.data.datasource.remote.RemoteDataSource
import com.hegunhee.maplefinder.data.mapper.toCharacterDojang
import com.hegunhee.maplefinder.domain.repository.Repository
import com.hegunhee.maplefinder.model.character.CharacterDojang
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getCharacterDojangInfo(characterName: String,date : String): Result<CharacterDojang> = coroutineScope {
        runCatching {
            val ocid = remoteDataSource.getCharacterOcid(characterName).id
            val characterDojangResponse = remoteDataSource.getCharacterDojang(ocid,date).toCharacterDojang(characterName)
            characterDojangResponse
        }
    }

}