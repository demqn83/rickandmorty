package com.example.rickandmorty.presentation.listcharacters

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
import com.example.rickandmorty.databinding.FragmentListCharactersBinding
import kotlinx.serialization.ExperimentalSerializationApi


@ExperimentalSerializationApi
class ListCharactersFragment : Fragment(R.layout.fragment_list_characters) {

    private var listener: EventListCharacters? = null

    private var _binding: FragmentListCharactersBinding? = null
    private val binding get() = _binding!!

    private val listCharactersViewModel: ListCharactersViewModel by viewModels {
        ListCharactersViewModelFactory(App().interactor, App().domainToUiMapper)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewTitleToolbar.text = "Список персонажей"

        val adapter = CharactersAdapter(
            {
                listCharactersViewModel.getAllCharacters()
            }, (
                    { id ->
                        listener?.startCharacterInfo(id)
                    })
        )
        binding.rvListCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListCharacters.adapter = adapter
        binding.rvListCharacters.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        listCharactersViewModel.listCharacters.observe(viewLifecycleOwner)
        {
            adapter.update(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventListCharacters) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    interface EventListCharacters {
        fun startCharacterInfo(id: Int)
    }
}