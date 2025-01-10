package com.hegunhee.maplefinder.data.api

import com.hegunhee.maplefinder.data.api.model.error.ErrorDetail
import com.hegunhee.maplefinder.data.api.model.error.ErrorResponse
import com.hegunhee.maplefinder.model.exception.NexonApiException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.json.Json
import retrofit2.HttpException

@OptIn(ExperimentalSerializationApi::class)
suspend fun <T, R> T.createResult(call : suspend T.() -> R) : Result<R> {
    return try {
        Result.success(call())
    } catch (httpE: HttpException) {
        Result.failure(when (httpE.code()) {
            500 -> { NexonApiException.ServerException }
            429 -> { NexonApiException.TooManyRequestException }
            403 -> { NexonApiException.ForbiddenException }
            400 -> {
                val errorResponse = json.decodeFromString<ErrorResponse>(httpE.getJsonErrorBodyString())
                create400CustomException(errorResponse.errorDetail)
            }
            else -> { httpE }
        })
    } catch (missingFieldE: MissingFieldException) {
        Result.failure(NexonApiException.UnretrivableException)
    }catch (e: RuntimeException) {
        Result.failure(e)
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

private fun HttpException.getJsonErrorBodyString(): String{
    return response()?.errorBody()?.string() ?: ""
}

private fun create400CustomException(errorDetail: ErrorDetail): NexonApiException {
    return when (errorDetail.name) {
        "OPENAPI00005" -> {
            NexonApiException.InvailedApiKeyException
        }
        "OPENAPI00004" -> {
            NexonApiException.InvailedParameterException
        }
        else -> {
            NexonApiException.IllegalStateException
        }
    }
}

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    coerceInputValues = true
}
