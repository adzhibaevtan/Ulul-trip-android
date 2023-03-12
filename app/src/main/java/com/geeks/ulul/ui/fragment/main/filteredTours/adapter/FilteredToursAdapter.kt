package com.geeks.ulul.ui.fragment.main.filteredTours.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geeks.ulul.data.model.TourModel
import com.geeks.ulul.data.util.getRegions
import com.geeks.ulul_trip_android.databinding.ItemFilteredTourBinding

class FilteredToursAdapter(val onTourClick: (TourModel) -> Unit) :
    PagingDataAdapter<TourModel, FilteredToursAdapter.TourModelPagingViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: TourModelPagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourModelPagingViewHolder {
        return TourModelPagingViewHolder(
            ItemFilteredTourBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    inner class TourModelPagingViewHolder(
        private val binding: ItemFilteredTourBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TourModel) {
            with(binding) {

                tvTitle.text = model.title
                tvRegion.text = model.region.getRegions()

                itemView.setOnClickListener {
                    onTourClick(model)
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TourModel>() {
            override fun areItemsTheSame(oldItem: TourModel, newItem: TourModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TourModel, newItem: TourModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}