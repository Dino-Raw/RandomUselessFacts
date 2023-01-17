package com.example.randomuselessfacts.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domian.model.FactModel
import com.example.randomuselessfacts.databinding.RowHistoryFactsBinding
import com.example.randomuselessfacts.presentation.viewholder.HistoryFactsViewHolder
import com.example.randomuselessfacts.presentation.viewmodel.HistoryFactsViewModel
import javax.inject.Inject

class HistoryFactsAdapter @Inject constructor(): RecyclerView.Adapter<HistoryFactsViewHolder>() {
    private val historyList = ArrayList<FactModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(historyList: ArrayList<FactModel>) {
        this.historyList.clear()
        this.historyList.addAll(historyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryFactsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowHistoryFactsBinding.inflate(layoutInflater)
        return HistoryFactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryFactsViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount() = historyList.size
}