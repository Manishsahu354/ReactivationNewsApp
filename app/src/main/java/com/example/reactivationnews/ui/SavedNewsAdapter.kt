package com.example.reactivationnews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.reactivationnews.data.model.ArticlesItem
import com.example.reactivationnews.databinding.NewsItemLayoutBinding

class SavedNewsAdapter(
    private val onItemClick: (ArticlesItem) -> Unit,
    private val onBookmarkClick: (ArticlesItem) -> Unit
) : ListAdapter<ArticlesItem, NewsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, onItemClick = { position ->
            val article = getItem(position)
            if (article != null) {
                onItemClick(article)
            }
        },
            onBookmarkClick = { position ->
                val article = getItem(position)
                if (article != null) {
                    onBookmarkClick(article)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(
            oldItem: ArticlesItem,
            newItem: ArticlesItem
        ) =
            oldItem.source?.id == newItem.source?.id

        override fun areContentsTheSame(
            oldItem: ArticlesItem,
            newItem: ArticlesItem
        ) =
            oldItem == newItem
    }
}