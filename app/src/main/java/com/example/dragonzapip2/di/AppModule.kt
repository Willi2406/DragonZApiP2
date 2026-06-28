package com.example.dragonzapip2.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.dragonzapip2.data.remote.personaje.CharacterRemoteDataSource
import com.example.dragonzapip2.data.remote.DragonBallApi
import com.example.dragonzapip2.data.remote.personaje.repository.CharacterRepositoryImp
import com.example.dragonzapip2.data.remote.planet.PlanetRemoteDataSource
import com.example.dragonzapip2.data.remote.planet.repository.PlanetRepositoryImp
import com.example.dragonzapip2.domain.personaje.repository.CharacterRepository
import com.example.dragonzapip2.domain.planet.repository.PlanetRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
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
    fun providePlanetRemoteDataSource(api: DragonBallApi): PlanetRemoteDataSource =
        PlanetRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: PlanetRemoteDataSource): PlanetRepository =
        PlanetRepositoryImp(remoteDataSource)

    @Provides
    @Singleton
    fun provideCharacterRemoteDataSource(api: DragonBallApi): CharacterRemoteDataSource =
        CharacterRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideCharacterRepository(remoteDataSource: CharacterRemoteDataSource): CharacterRepository =
        CharacterRepositoryImp(remoteDataSource)
}