package com.geeks.ulul.ui.fragment.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geeks.ulul.data.model.SlugModel
import com.geeks.ulul_trip_android.databinding.ItemSearchHintBinding

class HintAdapter(private val onHintClick: (String) -> Unit) : Adapter<HintAdapter.HintViewHolder>() {

    private val data = arrayListOf<SlugModel>()

    fun addData(newData: List<SlugModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HintViewHolder {
        return HintViewHolder(ItemSearchHintBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HintViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class HintViewHolder(private val binding: ItemSearchHintBinding) : ViewHolder(binding.root) {
        fun bind(model: SlugModel) {
            binding.tvHint.text = model.title

            itemView.setOnClickListener {
                onHintClick(model.slug)
            }
        }
    }
}