package com.sesac.planet.presentation.view.main.planet_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sesac.planet.data.model.ResultPlanetDetailPlan
import com.sesac.planet.databinding.ItemPlanetDetailDetailsPlanBinding

class PlanetDetailAdapter(val items: List<ResultPlanetDetailPlan>) :
    RecyclerView.Adapter<PlanetDetailAdapter.PlanetDetailViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlanetDetailViewHolder {
        val binding = ItemPlanetDetailDetailsPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanetDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetDetailViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = items!!.size

    inner class PlanetDetailViewHolder(private val binding: ItemPlanetDetailDetailsPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var isCompleted: Boolean = false
            if(items!![position].is_completed==0) {
                isCompleted = false
            } else if(items!![position].is_completed==1) {
                isCompleted = true
            }

            binding.itemPlanetDetailToDoTextView.text = items!![position].plan_name
            binding.itemPlanetDetailDurationTv.text = items!![position].type
            //계획 성공 여부
            binding.itemPlanetDetailCheckBox.isChecked = isCompleted
        }
    }
}