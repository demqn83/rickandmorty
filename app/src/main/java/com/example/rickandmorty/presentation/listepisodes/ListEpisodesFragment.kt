package com.example.rickandmorty.presentation.listepisodes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.App
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentListEpisodesBinding
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class ListEpisodesFragment : Fragment(R.layout.fragment_list_episodes) {

    private var listener: EventListEpisodes? = null

    private var _binding: FragmentListEpisodesBinding? = null
    private val binding get() = _binding!!

    private val listEpisodesViewModel: ListEpisodesViewModel by viewModels {
        ListEpisodesViewModelFactory(App().interactor, App().domainToUiEpisodesMapper)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewTitleToolbar.text = "Список эпизодов"
        binding.imageViewToolbar.setOnClickListener {
            listener?.onBack()
        }
        val idCharacter = requireArguments().getInt("LIST_EPISODES_FRAGMENT")
        listEpisodesViewModel.getAllEpisodes(idCharacter)
        val adapter = EpisodesAdapter { listEpisodesViewModel.getAllEpisodes(idCharacter) }
        binding.rvListCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListCharacters.adapter = adapter
        binding.rvListCharacters.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        listEpisodesViewModel.listEpisodes.observe(viewLifecycleOwner)
        {
            adapter.update(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventListEpisodes) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    interface EventListEpisodes {
        fun onBack()
    }
}