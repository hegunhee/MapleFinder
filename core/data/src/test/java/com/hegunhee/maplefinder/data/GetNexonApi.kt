package com.hegunhee.maplefinder.data

import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun getMapleMoshi() : Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

internal fun getConverterFactory(
    json : Json = getJson()
) : Converter.Factory {
    return json.asConverterFactory("application/json".toMediaType())
}

internal fun getMapleOcidApi(converterFactory : Converter.Factory = getConverterFactory()) : MapleOcidApi =
    Retrofit.Builder()
        .baseUrl(BuildConfig.GET_OCID_MAPLE_BASE_URL)
        .addConverterFactory(converterFactory)
        .client(provideOkHttpClient(NexonInterceptor()))
        .build()
        .create(MapleOcidApi::class.java)

internal fun getMapleApi(converterFactory : Converter.Factory = getConverterFactory()) : MapleCharacterApi =
    Retrofit.Builder()
        .baseUrl(BuildConfig.GET_CHARACTER_MAPLE_BASE_URL)
        .addConverterFactory(converterFactory)
        .client(provideOkHttpClient(NexonInterceptor()))
        .build()
        .create(MapleCharacterApi::class.java)


private fun provideOkHttpClient(vararg interceptor: Interceptor) : OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    return OkHttpClient.Builder().run {
        addNetworkInterceptor(loggingInterceptor)
        interceptor.forEach { addInterceptor(it) }
        build()
    }
}

private class NexonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("x-nxopen-api-key", BuildConfig.MAPLE_API_KEY)
                .build()
            proceed(newRequest)
        }
    }
}

private fun getJson() : Json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}