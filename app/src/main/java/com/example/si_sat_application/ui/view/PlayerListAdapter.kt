package com.example.si_sat_application.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.si_sat_application.R
import com.example.si_sat_application.databinding.RowItemPlayersBinding
import com.example.si_sat_application.domain.models.Player

class PlayerListAdapter(
    private val context: Context
) : ListAdapter<Player, PlayerListAdapter.PlayerListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        val binding =
            RowItemPlayersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class PlayerListViewHolder(private val binding: RowItemPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Player: Player) {
            Player.apply {
                binding.textViewPlayerName.text = name
                if (isCaptain) {
                    binding.textViewPlayerName.append(context.getString(R.string.captain))
                }
                if (isKeeper) {
                    binding.textViewPlayerName.append(context.getString(R.string.wicket_keeper))
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Player, newItem: Player) =
            oldItem == newItem
    }
}