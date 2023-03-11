package com.geeks.ulul.ui.fragment.main.detailTour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geeks.ulul.data.model.TourImage
import com.geeks.ulul_trip_android.databinding.ItemDetailTourImageBinding

class DetailTourImagesAdapter : RecyclerView.Adapter<DetailTourImagesAdapter.DetailTourImagesViewHolder>() {

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
        fun bind(url: TourImage) {
            with(binding) {
                if (ivImage != null) {
                    Glide.with(ivImage).load(url.images).into(ivImage)
                }

//                else {
//                    Glide.with(binding.ivImage).load("https://img1.freepng.ru/20171220/uue/kyrgyzstan-flag-png-5a3a6c772563e6.8396222215137782951532.jpg").into(ivImage)
//                }
            }
        }
    }
}