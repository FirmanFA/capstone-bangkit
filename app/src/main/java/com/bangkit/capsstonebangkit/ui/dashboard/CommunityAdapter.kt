package com.bangkit.capsstonebangkit.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capsstonebangkit.data.api.model.CommunityResponse
import com.bangkit.capsstonebangkit.databinding.CommunityListLayoutBinding

class CommunityAdapter(private val onClick:(CommunityResponse.Community)->Unit)
    : ListAdapter<CommunityResponse.Community, CommunityAdapter.ViewHolder>(CommunityComparator()) {


    class ViewHolder(private val binding: CommunityListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentCommunity: CommunityResponse.Community,
                 onClick: (CommunityResponse.Community) -> Unit){

            binding.apply {
                btnCommunityName.text = currentCommunity.name
                root.setOnClickListener {
                    onClick(currentCommunity)
                }
            }

        }

    }

    class CommunityComparator : DiffUtil.ItemCallback<CommunityResponse.Community>() {
        override fun areItemsTheSame(oldItem: CommunityResponse.Community, newItem: CommunityResponse.Community): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CommunityResponse.Community, newItem: CommunityResponse.Community): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommunityListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

}