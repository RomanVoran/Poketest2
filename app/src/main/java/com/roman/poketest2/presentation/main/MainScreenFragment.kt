package com.roman.poketest2.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.poketest2.R
import com.roman.poketest2.databinding.FragmentMainScreenBinding
import com.roman.poketest2.presentation.main.adapter.PokemonListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding get() = _binding!!
    private val viewModel: MainScreenViewModel by viewModels()
    private val adapter = PokemonListAdapter { pokeId ->
        findNavController().navigate(R.id.action_mainScreenFragment_to_detailFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
        initObservers()
    }


    private fun initView() = with(binding) {
        pokemonList.adapter = adapter
        pokemonList.layoutManager = LinearLayoutManager(requireContext())
        setHasOptionsMenu(true)
    }

    private fun initListeners() {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            findNavController().navigate(R.id.action_mainScreenFragment_to_settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initObservers() {
        viewModel.eventErrorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            Log.e("TEST_TAG", errorMessage)
        }

        viewModel.showLoading.observe(viewLifecycleOwner) { isLoadingShow ->
            binding.progressCircular.isVisible = isLoadingShow
            if (isLoadingShow) {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                Log.w("TEST_TAG", "LOADING")
            } else {
                Log.w("TEST_TAG", "hide LOADING")
            }
        }

        viewModel.updatePokeList.observe(viewLifecycleOwner) { pokeList ->
            adapter.submitList(pokeList)
        }
    }

}