package com.example.si_sat_application.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.si_sat_application.databinding.FragmentMatchesBinding
import kotlinx.coroutines.launch

class MatchListFragment : Fragment() {
    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    private val matchViewModel: MatchViewModel by activityViewModels()

    private lateinit var matchListAdapter: MatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        collectState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        binding.apply {
            matchListAdapter = MatchListAdapter(
                onMatchClick = {matchId->
                    matchViewModel.updateSelectedMatchId(matchId = matchId)
                    val action =
                        MatchesFragmentDirections.actionTeamListFragmentToPlayerListFragment()
                    findNavController().navigate(action)
                }
            )

            val layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(requireActivity(), layoutManager.orientation)
            recyclerViewMatchList.layoutManager = layoutManager
            recyclerViewMatchList.addItemDecoration(dividerItemDecoration)
            recyclerViewMatchList.adapter = matchListAdapter
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                matchViewModel.uiState.collect { uiState ->
                    binding.progressBar.apply {
                        uiState.matchDetails.let { matchDetails ->
                            matchListAdapter.submitList(matchDetails)
                        }
                        if (uiState.isLoading) {
                            this.visibility = View.VISIBLE
                        } else {
                            this.visibility = View.GONE
                        }
                    }
                    uiState.message?.let {
                        Toast.makeText(
                            requireActivity(),
                            it.asString(requireActivity()),
                            Toast.LENGTH_SHORT
                        ).show()
                        matchViewModel.messageShown()
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MatchesDetailsFragment"
    }
}