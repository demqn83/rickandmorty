package com.example.rickandmorty.data.net

import com.example.rickandmorty.data.net.model.ResponseCharacter
import com.example.rickandmorty.data.net.model.ResponseData
import com.example.rickandmorty.data.net.model.ResponseEpisodes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyAPI {

    @GET("character")
    suspend fun getAllCharacters(): ResponseData

    @GET("character")
    suspend fun getAllCharactersNumber(@Query("page") number: Int): ResponseData

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): ResponseCharacter

    @GET("episode")
    suspend fun getAllEpisodes(): ResponseEpisodes

    @GET("episode")
    suspend fun getAllEpisodesNumber(@Query("page") number: Int): ResponseEpisodes
}