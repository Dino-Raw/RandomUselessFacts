package com.example.randomuselessfacts.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.domian.model.FactModel
import com.example.randomuselessfacts.databinding.RowHistoryFactsBinding

class HistoryFactsViewHolder(private val binding: RowHistoryFactsBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(fact: FactModel) {
        binding.fact = fact.text
        binding.executePendingBindings()
    }
}