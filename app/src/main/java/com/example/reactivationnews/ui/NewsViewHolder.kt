package com.example.reactivationnews.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reactivationnews.data.model.ArticlesItem
import com.example.reactivationnews.databinding.NewsItemLayoutBinding

class NewsViewHolder(
    private val binding: NewsItemLayoutBinding,
    private val onItemClick: (Int) -> Unit,
    private val onBookmarkClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: ArticlesItem) {
            binding.apply {
                tvNewsTitle.text = newsItem.title
                tvNewsSource.text = newsItem.source?.name
                tvNewsCreationDate.text = newsItem.publishedAt
                Glide.with(ivNews).load(newsItem.urlToImage).into(ivNews)
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClick(position)
                    }
                }
                imageViewBookmark.setOnClickListener{
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onBookmarkClick(position)
                    }
                }
            }
        }
    }