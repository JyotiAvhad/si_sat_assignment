package com.example.si_sat_application.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.si_sat_application.databinding.RowItemMatchBinding
import com.rahulghag.siassignment.domain.models.MatchDetails

class MatchListAdapter(
    private val onMatchClick: (String) -> Unit
) : ListAdapter<MatchDetails, MatchListAdapter.MatchListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        val binding =
            RowItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MatchListViewHolder(private val binding: RowItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onMatchClick.invoke(getItem(position).series.id)
                    }
                }
            }
        }

        fun bind(matchDetails: MatchDetails) {
            matchDetails.apply {
                binding.textViewTeams.text = teamNames
                binding.textViewSeriesName.text = series.name
                binding.textViewVenue.text = venue.name
                binding.textViewSeriesOfficials.text = officials.referee
                binding.textViewWeather.text = weather
                binding.textViewResult.text = result
                binding.textViewSeriesStatus.text = series.status
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MatchDetails>() {
        override fun areItemsTheSame(oldItem: MatchDetails, newItem: MatchDetails) =
            oldItem.series.id == newItem.series.id

        override fun areContentsTheSame(oldItem: MatchDetails, newItem: MatchDetails) =
            oldItem == newItem
    }
}