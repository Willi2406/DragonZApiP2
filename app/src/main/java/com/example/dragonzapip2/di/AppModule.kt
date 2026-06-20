package com.example.dragonzapip2.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.dragonzapip2.data.remote.DragonBallApi
import com.example.dragonzapip2.data.repository.PlanetRepositoryImp
import com.example.dragonzapip2.domain.repository.PlanetRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule{
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi = Retrofit.Builder()
        .baseUrl("https://dragonball-api.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build().create(DragonBallApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: DragonBallApi): PlanetRepository = PlanetRepositoryImp(api)
}