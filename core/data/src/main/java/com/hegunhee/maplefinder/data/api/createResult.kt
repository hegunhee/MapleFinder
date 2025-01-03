package com.hegunhee.maplefinder.data.api

import com.hegunhee.maplefinder.model.exception.NexonApiException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
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
            400 -> { NexonApiException.InvailedParameterException }
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
