package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidListItemBinding

class AsteroidListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Asteroid, AsteroidListAdapter.ViewHolder>(DiffCallback) {
        //
        // ViewHolder
        //
        class ViewHolder(private var binding: AsteroidListItemBinding):
            RecyclerView.ViewHolder(binding.root) {
            fun bind(asteroid: Asteroid) {
                binding.asteroid = asteroid
                binding.executePendingBindings()
            }
        }

        //
        // DiffCallback
        //
        companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
            override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                return oldItem.id == newItem.id
            }
        }

        //
        // onX-functions
        //
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): ViewHolder {
            return ViewHolder(AsteroidListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val asteroid = getItem(position)
            holder.itemView.setOnClickListener {
                onClickListener.onClick(asteroid)
            }
            holder.bind(asteroid)
        }

        //
        // OnClickListener
        //
        class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
            fun onClick(asteroid: Asteroid) = clickListener(asteroid)
        }
}