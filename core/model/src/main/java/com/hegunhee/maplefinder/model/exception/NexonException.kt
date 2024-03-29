package com.hegunhee.maplefinder.model.exception

import java.io.IOException

sealed class NexonApiException(val code : Int, override val message : String) : Exception() {

    object ServerException : NexonApiException(code = 500, message = "서버 내부 오류가 발생했습니다.")

    object TooManyRequestException : NexonApiException(code = 429, message = "API 호출량 초과")

    object ForbiddenException : NexonApiException(code = 403, message = "권한이 없습니다.")

    object InvailedParameterException : NexonApiException(code = 400, message = "캐릭터 이름이 유효하지 않습니다")
}