package com.eyubero.fumblebook.di

import com.eyubero.fumblebook.BuildConfig
import com.eyubero.fumblebook.provider.ApiProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    @Named("Moshi")
    fun providesMoshi(): Moshi =  Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun providesRetrofit(@Named("BaseUrl") baseUrl: HttpUrl, @Named("Moshi") moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiProvider(retrofit: Retrofit): ApiProvider =
        retrofit.create(ApiProvider::class.java)
}