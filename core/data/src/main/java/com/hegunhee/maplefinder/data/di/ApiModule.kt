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
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideMapleMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideMapleOcidApi(
        moshi : Moshi
    ) : MapleOcidApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GET_OCID_MAPLE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(NexonInterceptor()))
            .build()
            .create(MapleOcidApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMapleApi(
        moshi : Moshi
    ) : MapleCharacterApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GET_CHARACTER_MAPLE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient(NexonInterceptor()))
            .build()
            .create(MapleCharacterApi::class.java)
    }

    private fun provideOkHttpClient(vararg interceptor: Interceptor) : OkHttpClient =
        OkHttpClient.Builder().run {
            interceptor.forEach { addInterceptor(it) }
            build()
        }


    private class NexonInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            return with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("x-nxopen-api-key",BuildConfig.MAPLE_API_KEY)
                    .build()
                proceed(newRequest)
            }
        }
    }
}