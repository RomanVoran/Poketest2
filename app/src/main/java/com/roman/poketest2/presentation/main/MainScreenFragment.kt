package com.roman.poketest2.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.poketest2.R
import com.roman.poketest2.databinding.FragmentMainScreenBinding
import com.roman.poketest2.domain.ListFormat
import com.roman.poketest2.domain.PokemonUi
import com.roman.poketest2.presentation.main.adapter.PokemonListAdapter
import com.roman.poketest2.presentation.settings.SettingsFragment
import com.roman.poketest2.utils.CHANGE_LIST_FORMAT_RESULT_REQUEST_KEY
import com.roman.poketest2.utils.CLEAR_LIST_RESULT_REQUEST_KEY
import com.roman.poketest2.utils.POKEMON_UI_TRANSACTION_KEY
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding get() = _binding!!
    private val viewModel: MainScreenViewModel by viewModels()
    private val adapter = PokemonListAdapter { pokemonUi -> openDetailsFragment(pokemonUi) }

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
        viewModel.fetchPokemons()
    }

    private fun initListeners() = with(binding) {
        swipeLayout.setOnRefreshListener {
            viewModel.fetchPokemons()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) showSettingsDialog()
        return super.onOptionsItemSelected(item)
    }


    private fun showSettingsDialog() {
        SettingsFragment().show(childFragmentManager, "SETTINGS")
        childFragmentManager.setFragmentResultListener(CLEAR_LIST_RESULT_REQUEST_KEY, this)
        { key, bundle ->
            viewModel.clearAll()
        }
        childFragmentManager.setFragmentResultListener(CHANGE_LIST_FORMAT_RESULT_REQUEST_KEY, this)
        { key, bundle ->
            val format =
                bundle.getString(CHANGE_LIST_FORMAT_RESULT_REQUEST_KEY).let { listFormatName ->
                    ListFormat.fromString(listFormatName)
                }
            when (format) {
                ListFormat.LIST -> {
                    binding.pokemonList.layoutManager = LinearLayoutManager(requireContext())
                }

                ListFormat.GRID -> {
                    binding.pokemonList.layoutManager = GridLayoutManager(requireContext(), 3)
                }
            }
        }
    }


    private fun initObservers() {
        viewModel.eventErrorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            Log.e("TEST_TAG", errorMessage)
        }

        viewModel.showLoading.observe(viewLifecycleOwner) { isLoadingShow ->
            binding.progressCircular.isVisible = isLoadingShow
            binding.swipeLayout.isRefreshing = isLoadingShow
        }

        viewModel.updatePokeList.observe(viewLifecycleOwner) { pokeList ->
            adapter.submitList(pokeList)
        }
    }

    private fun openDetailsFragment(pokemonUi: PokemonUi) {
        findNavController().navigate(
            R.id.action_mainScreenFragment_to_detailFragment,
            bundleOf(Pair(POKEMON_UI_TRANSACTION_KEY, pokemonUi))
        )
    }

}