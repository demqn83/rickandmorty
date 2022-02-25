package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.Repository
import com.example.rickandmorty.data.net.RickAndMortyAPI
import com.example.rickandmorty.domain.DataToDomainEpisodesMapperImpl
import com.example.rickandmorty.domain.DataToDomainFullInfoMapperImpl
import com.example.rickandmorty.domain.DataToDomainMapperImpl
import com.example.rickandmorty.domain.Interactor
import com.example.rickandmorty.presentation.characterInfo.DomainToUiFullInfoMapper
import com.example.rickandmorty.presentation.characterInfo.DomainToUiFullInfoMapperImpl
import com.example.rickandmorty.presentation.listcharacters.DomainToUiMapper
import com.example.rickandmorty.presentation.listcharacters.DomainToUiMapperImpl
import com.example.rickandmorty.presentation.listepisodes.DomainToUiEpisodesMapper
import com.example.rickandmorty.presentation.listepisodes.DomainToUiEpisodesMapperImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

@ExperimentalSerializationApi
class App : Application() {

    private companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    private val rickAndMortyAPI: RickAndMortyAPI
    val interactor: Interactor
    val domainToUiMapper: DomainToUiMapper
    val domainToUiFullInfoMapper: DomainToUiFullInfoMapper
    val domainToUiEpisodesMapper: DomainToUiEpisodesMapper

    init {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
        rickAndMortyAPI = retrofit.create()

        val repository = Repository(rickAndMortyAPI)
        interactor =
            Interactor(
                repository,
                DataToDomainMapperImpl(),
                DataToDomainFullInfoMapperImpl(),
                DataToDomainEpisodesMapperImpl()
            )
        domainToUiMapper = DomainToUiMapperImpl()
        domainToUiFullInfoMapper = DomainToUiFullInfoMapperImpl()
        domainToUiEpisodesMapper = DomainToUiEpisodesMapperImpl()
    }
}