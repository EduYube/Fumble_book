package com.eyubero.fumblebook.di

import com.eyubero.fumblebook.BuildConfig
import com.eyubero.fumblebook.provider.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {
    @Provides
    @Named("BaseUrl")
    fun providesBaseUrl() = BuildConfig.BASE_URL.toHttpUrl()

    @Singleton
    @Provides
    fun providesRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiProvider(retrofit: Retrofit): ApiProvider =
        retrofit.create(ApiProvider::class.java)
}