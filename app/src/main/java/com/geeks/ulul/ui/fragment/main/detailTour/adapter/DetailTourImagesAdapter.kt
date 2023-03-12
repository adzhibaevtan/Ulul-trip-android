package com.geeks.ulul.ui.fragment.main.detailTour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.geeks.ulul.data.model.TourImage
import com.geeks.ulul.data.util.correctUrl
import com.geeks.ulul.data.util.load
import com.geeks.ulul_trip_android.databinding.ItemDetailTourImageBinding

class DetailTourImagesAdapter : Adapter<DetailTourImagesAdapter.DetailTourImagesViewHolder>() {

    val data = arrayListOf<TourImage>()

    fun addData(newData: List<TourImage>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTourImagesViewHolder {
        return DetailTourImagesViewHolder(
            ItemDetailTourImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: DetailTourImagesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class DetailTourImagesViewHolder(private val binding: ItemDetailTourImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TourImage) {
            with(binding) {
                ivImage.load(model.images.correctUrl())
            }
        }
    }
}