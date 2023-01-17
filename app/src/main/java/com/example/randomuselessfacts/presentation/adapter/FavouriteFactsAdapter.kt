package com.example.randomuselessfacts.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domian.model.FactModel
import com.example.randomuselessfacts.databinding.RowFavouriteFactsBinding
import com.example.randomuselessfacts.presentation.viewholder.FavouriteFactsViewHolder
import javax.inject.Inject

class FavouriteFactsAdapter @Inject constructor(): RecyclerView.Adapter<FavouriteFactsViewHolder>() {
    private val favouriteList = ArrayList<FactModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(favouriteList: ArrayList<FactModel>) {
        this.favouriteList.clear()
        this.favouriteList.addAll(favouriteList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        favouriteList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteFactsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowFavouriteFactsBinding.inflate(layoutInflater)
        return FavouriteFactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteFactsViewHolder, position: Int) {
        holder.bind(favouriteList[position])
    }

    override fun getItemCount() = favouriteList.size
}