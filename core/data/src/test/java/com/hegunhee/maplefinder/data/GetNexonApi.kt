package com.hegunhee.maplefinder.data

import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun getMapleMoshi() : Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

internal fun getMapleOcidApi(moshi : Moshi = getMapleMoshi()) : MapleOcidApi =
    Retrofit.Builder()
        .baseUrl(BuildConfig.GET_OCID_MAPLE_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(provideOkHttpClient(NexonInterceptor()))
        .build()
        .create(MapleOcidApi::class.java)

internal fun getMapleApi(moshi : Moshi = getMapleMoshi()) : MapleCharacterApi =
    Retrofit.Builder()
        .baseUrl(BuildConfig.GET_CHARACTER_MAPLE_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(provideOkHttpClient(NexonInterceptor()))
        .build()
        .create(MapleCharacterApi::class.java)


private fun provideOkHttpClient(vararg interceptor: Interceptor) : OkHttpClient =
    OkHttpClient.Builder().run {
        interceptor.forEach { addInterceptor(it) }
        build()
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