package com.agri.connect.ui.crop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agri.connect.databinding.ItemCropBinding
import com.agri.connect.data.local.entities.CropGuide

class CropAdapter : ListAdapter<CropGuide, CropAdapter.CropVH>(DiffCallback()) {
    class CropVH(private val binding: ItemCropBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CropGuide) {
            binding.tvCropName.text = item.name
            binding.tvCropDesc.text = item.description
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropVH {
        val b = ItemCropBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CropVH(b)
    }
    override fun onBindViewHolder(holder: CropVH, position: Int) {
        holder.bind(getItem(position))
    }
    class DiffCallback : DiffUtil.ItemCallback<CropGuide>() {
        override fun areItemsTheSame(oldItem: CropGuide, newItem: CropGuide) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CropGuide, newItem: CropGuide) = oldItem == newItem
    }
}
