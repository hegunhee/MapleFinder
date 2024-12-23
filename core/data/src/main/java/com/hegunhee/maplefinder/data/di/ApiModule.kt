package com.hegunhee.maplefinder.data.di

import com.hegunhee.maplefinder.data.api.MapleOcidApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import javax.inject.Singleton
import com.hegunhee.maplefinder.data.BuildConfig
import com.hegunhee.maplefinder.data.api.MapleCharacterApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideMapleMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideConverterFactory(
        json: Json,
    ): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideMapleOcidApi(
        converterFactory: Converter.Factory
    ): MapleOcidApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GET_OCID_MAPLE_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(provideOkHttpClient(NexonInterceptor()))
            .build()
            .create(MapleOcidApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMapleApi(
        converterFactory: Converter.Factory
    ): MapleCharacterApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GET_CHARACTER_MAPLE_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(provideOkHttpClient(NexonInterceptor()))
            .build()
            .create(MapleCharacterApi::class.java)
    }

    private fun provideOkHttpClient(vararg interceptor: Interceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
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

}
