package com.example.si_sat_application.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.si_sat_application.R
import com.example.si_sat_application.databinding.FragmentPlayersBinding


class PlayerListFragment : Fragment() {
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    private val matchViewModel: MatchViewModel by activityViewModels()

    private lateinit var teamOnePlayerAdapter: PlayerListAdapter
    private lateinit var teamTwoPlayerAdapter: PlayerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_player_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_all_players -> {
                binding.layoutTeamOne.visibility = View.VISIBLE
                binding.layoutTeamTwo.visibility = View.VISIBLE
            }
            R.id.action_team_one -> {
                binding.layoutTeamTwo.visibility = View.GONE
                binding.layoutTeamOne.visibility = View.VISIBLE
            }
            R.id.action_team_two -> {
                binding.layoutTeamOne.visibility = View.GONE
                binding.layoutTeamTwo.visibility = View.VISIBLE
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUI() {
        binding.apply {
            matchViewModel.getMatchDetailsById()?.apply {
                textViewTeamOneName.text = teams[0].name
                textViewTeamTwoName.text = teams[1].name

                teamOnePlayerAdapter = PlayerListAdapter(context = requireActivity())
                teamTwoPlayerAdapter = PlayerListAdapter(context = requireActivity())

                val layoutManager1 =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                val dividerItemDecoration =
                    DividerItemDecoration(requireActivity(), layoutManager1.orientation)
                rVTeamOnePlayers.layoutManager = layoutManager1
                rVTeamOnePlayers.addItemDecoration(dividerItemDecoration)
                rVTeamOnePlayers.adapter = teamOnePlayerAdapter
                teamOnePlayerAdapter.submitList(teams[0].players)

                val layoutManager2 =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                rVTeamTwoPlayers.layoutManager = layoutManager2
                rVTeamTwoPlayers.addItemDecoration(dividerItemDecoration)
                rVTeamTwoPlayers.adapter = teamTwoPlayerAdapter
                teamTwoPlayerAdapter.submitList(teams[1].players)
            }
        }
    }

    companion object {
        private const val TAG = "TeamsFragment"
    }
}