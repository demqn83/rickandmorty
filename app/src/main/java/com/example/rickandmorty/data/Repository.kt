package com.example.rickandmorty.data

import com.example.rickandmorty.data.net.ConstNet.adressCharacter
import com.example.rickandmorty.data.net.ConstNet.adressCharacterPage
import com.example.rickandmorty.data.net.ConstNet.adressEpisodePage
import com.example.rickandmorty.data.net.RickAndMortyAPI

class Repository(
    private val rickAndMortyAPI: RickAndMortyAPI
) {
    suspend fun getAllCharacters(): CharactersData {
        return try {
            val results = mutableListOf<CharacterData>()
            val response = rickAndMortyAPI.getAllCharacters()
            response.results.forEach {
                results.add(CharacterData(it.id, it.name, it.image))
            }
            var nextNumber = response.info.next
            while (nextNumber != null) {
                val nextNumberInt =
                    nextNumber.replace(adressCharacterPage, "")
                        .toInt()
                val responseNext = rickAndMortyAPI.getAllCharactersNumber(nextNumberInt)
                responseNext.results.forEach {
                    results.add(CharacterData(it.id, it.name, it.image))
                }
                nextNumber = responseNext.info.next
            }
            CharactersData.Success(results)
        } catch (e: Exception) {
            CharactersData.Fail(e)
        }
    }

    suspend fun getCharacter(id: Int): CharacterFullInfoData {
        return try {
            val response = rickAndMortyAPI.getCharacter(id)
            CharacterFullInfoData.Success(
                response.id,
                response.image,
                response.name,
                response.location.name,
                response.status,
                response.species
            )
        } catch (e: Exception) {
            CharacterFullInfoData.Fail(e)
        }
    }

    suspend fun getAllEpisodes(id: Int): EpisodesData {
        return try {
            val results = mutableListOf<EpisodeData>()
            val response = rickAndMortyAPI.getAllEpisodes()

            response.results.forEach {
                if (it.characters.any { character ->
                        character.replace(adressCharacter, "")
                            .toInt() == id
                    }) {
                    results.add(
                        EpisodeData(
                            it.name, it.air_date
                        )
                    )
                }
            }
            var nextNumber = response.info.next
            while (nextNumber != null) {
                val nextNumberInt =
                    nextNumber.replace(adressEpisodePage, "")
                        .toInt()
                val responseNext = rickAndMortyAPI.getAllEpisodesNumber(nextNumberInt)
                responseNext.results.forEach {
                    if (it.characters.any { character ->
                            character.replace(adressCharacter, "")
                                .toInt() == id
                        }) {
                        results.add(
                            EpisodeData(
                                it.name, it.air_date
                            )
                        )
                    }
                }
                nextNumber = responseNext.info.next
            }
            EpisodesData.Success(results)
        } catch (e: Exception) {
            EpisodesData.Fail(e)
        }
    }
}
