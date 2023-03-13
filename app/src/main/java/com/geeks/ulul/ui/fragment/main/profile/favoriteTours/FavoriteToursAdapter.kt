package com.geeks.ulul.ui.fragment.main.profile.favoriteTours

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geeks.ulul.data.remote.dto.dtoo.FavoriteTourDto
import com.geeks.ulul.data.util.getDuration
import com.geeks.ulul_trip_android.databinding.ItemFilteredTourBinding

class FavoriteToursAdapter(val onTourClick: (FavoriteTourDto) -> Unit) :
    PagingDataAdapter<FavoriteTourDto, FavoriteToursAdapter.TourModelPagingViewHolder>(diffCallback) {
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
        @SuppressLint("SetTextI18n")
        fun bind(model: FavoriteTourDto) {
            with(binding) {
//                setImage(model)
                tvTitle.text = model.title
                tvReviews.text = "0 отзывов"
//                tvRegion.text = model.region.getRegions()
//                tvCategory.text = model.category.name
                tvPrice.text = model.price.toString()
                tvDuration.text = "за ${model.duration.getDuration()}"
                itemView.setOnClickListener {
                    onTourClick(model)
                }
            }
        }

//        private fun setImage(model: FavoriteTourDto) {
//            model.
//            if (model..isNotEmpty()) {
//                for (i in model.tour_images) {
//                    if (i.is_main) {
//                        binding.ivImage.load(i.images.correctUrl())
//                    }
//                }
//            }
//        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<FavoriteTourDto>() {
            override fun areItemsTheSame(
                oldItem: FavoriteTourDto,
                newItem: FavoriteTourDto
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteTourDto,
                newItem: FavoriteTourDto
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}