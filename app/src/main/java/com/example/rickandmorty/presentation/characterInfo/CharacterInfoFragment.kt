package com.example.rickandmorty.presentation.characterInfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.rickandmorty.App
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterInfoBinding

class CharacterInfoFragment : Fragment(R.layout.fragment_character_info) {

    private var listener: EventCharacterInfo? = null

    private var _binding: FragmentCharacterInfoBinding? = null
    private val binding get() = _binding!!

    private val characterInfoViewModel: CharacterInfoViewModel by viewModels {
        CharacterInfoViewModelFactory(App().interactor, App().domainToUiFullInfoMapper)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewToolbar.setOnClickListener {
            listener?.onBack()
        }
        val id = requireArguments().getInt(MainActivity.LIST_CHARACTERS_FRAGMENT_EVENT)
        characterInfoViewModel.getCharacter(id)
        characterInfoViewModel.characterFullInfoUI.observe(viewLifecycleOwner) { character ->
            when (character) {
                is CharacterFullInfoUI.Success -> {
                    binding.textViewTitleToolbar.text = character.name
                    binding.textViewNameCharacter.text = character.name
                    binding.textViewLocation.text = character.location
                    binding.textViewSpecies.text = character.species
                    binding.textViewStatus.text = character.status
                    Glide
                        .with(this)
                        .load(character.image)
                        .into(binding.imageCharacter)
                    binding.textViewEpisodeList.setOnClickListener {
                        listener?.startListEpisodesFragment(character.id)
                    }
                }
                is CharacterFullInfoUI.Fail -> {
                    binding.textViewError.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventCharacterInfo) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    interface EventCharacterInfo {
        fun onBack()
        fun startListEpisodesFragment(id: Int)
    }
}