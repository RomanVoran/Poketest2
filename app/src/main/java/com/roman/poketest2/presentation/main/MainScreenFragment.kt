package com.roman.poketest2.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.roman.poketest2.R
import com.roman.poketest2.databinding.FragmentDetailBinding
import com.roman.poketest2.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding get() = _binding!!
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.eventErrorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            Log.e("TEST_TAG", errorMessage)
        }
        viewModel.showLoading.observe(viewLifecycleOwner) { isLoadingShow ->
            if (isLoadingShow) {
                //TODO(ПОКАЗАТЬ процесс загрузки)
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Log.w("TEST_TAG", "LOADING")
            } else {
                //TODO(СКРЫТЬ процесс загрузки)
                Log.w("TEST_TAG", "hide LOADING")
            }
        }
        viewModel.updatePokeList.observe(viewLifecycleOwner) { pokeList ->
            pokeList.forEach { pokemon ->
                Log.e("TEST_TAG", "[ id = ${pokemon.id}; name = ${pokemon.name} ]")
            }
        }

        binding.loadButton.setOnClickListener {
            viewModel.fetchPokemon()
        }

        binding.testButton.setOnClickListener {
            viewModel.getLocal()
        }

        binding.removeButton.setOnClickListener {
            viewModel.clearAll()
        }

    }

}