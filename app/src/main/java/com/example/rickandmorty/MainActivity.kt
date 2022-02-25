package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.rickandmorty.presentation.characterInfo.CharacterInfoFragment
import com.example.rickandmorty.presentation.listcharacters.ListCharactersFragment
import com.example.rickandmorty.presentation.listepisodes.ListEpisodesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main),
    ListEpisodesFragment.EventListEpisodes, CharacterInfoFragment.EventCharacterInfo,
    ListCharactersFragment.EventListCharacters {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                    .add<ListCharactersFragment>(R.id.container_fragment)
            }
        }
    }

    override fun onBack() {
        supportFragmentManager.popBackStack()
    }

    override fun startListEpisodesFragment(id: Int) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<ListEpisodesFragment>(
                R.id.container_fragment,
                args = bundleOf(LIST_EPISODES_FRAGMENT to id)
            )
        }
    }

    override fun startCharacterInfo(id: Int) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<CharacterInfoFragment>(
                R.id.container_fragment,
                args = bundleOf(LIST_CHARACTERS_FRAGMENT_EVENT to id)
            )
        }
    }

    companion object {
        const val LIST_CHARACTERS_FRAGMENT_EVENT = "LIST_CHARACTERS_FRAGMENT_EVENT"
        const val LIST_EPISODES_FRAGMENT = "LIST_EPISODES_FRAGMENT"
    }
}