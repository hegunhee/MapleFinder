package com.hegunhee.maplefinder.data.api

import com.hegunhee.maplefinder.model.exception.NexonApiException
import retrofit2.HttpException

suspend fun <T, R> T.createResult(call : suspend T.() -> R) : Result<R> {
    return try {
        Result.success(call())
    } catch (httpE: HttpException) {
        Result.failure(when (httpE.code()) {
            500 -> { NexonApiException.ServerException }
            429 -> { NexonApiException.TooManyRequestException }
            403 -> { NexonApiException.ForbiddenException }
            400 -> { NexonApiException.InvailedParameterException }
            else -> { httpE }
        })
    } catch (e: Throwable) {
        Result.failure(e)
    }
}