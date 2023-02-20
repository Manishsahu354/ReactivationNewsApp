package com.example.reactivationnews

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reactivationnews.databinding.ActivityMainBinding
import com.example.reactivationnews.ui.NewsAdapter
import com.example.reactivationnews.ui.NewsArticleLoadStateAdapter
import com.example.reactivationnews.ui.NewsDetailsActivity
import com.example.reactivationnews.ui.SavedNewsAdapter
import com.example.reactivationnews.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getTopHeadlines()
    }
    private fun getTopHeadlines(){
        setupTopHeadlineRecyclerview()
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        newsViewModel.saveNewsLiveData.removeObservers(this)
        newsViewModel.newsData.observe(this) {
            newsAdapter.submitData(lifecycle,it)
        }
    }
    private fun setupTopHeadlineRecyclerview() {
        newsAdapter = NewsAdapter(
            onItemClick = { article ->
                val intent = Intent(this@MainActivity,NewsDetailsActivity::class.java)
                intent.putExtra("web_url",article.url)
                startActivity(intent)
            },
            onBookmarkClick = {article->
                newsViewModel.bookmarksNews(article)
                Toast.makeText(this@MainActivity,"News Saved",Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter.withLoadStateFooter(
                NewsArticleLoadStateAdapter(newsAdapter::retry)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.topHeadlines -> {
                getTopHeadlines()
            }
            R.id.savedNews -> {
                getSavedNews()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getSavedNews(){
        val savedNewsAdapter = SavedNewsAdapter(
            onItemClick = { article ->
                val intent = Intent(this@MainActivity,NewsDetailsActivity::class.java)
                intent.putExtra("web_url",article.url)
                startActivity(intent)
            },
            onBookmarkClick = {article->
                newsViewModel.removeBookmarkNews(article)
                Toast.makeText(this@MainActivity,"News Removed",Toast.LENGTH_SHORT).show()
                newsViewModel.getAllSavedNews()
            }
        )
        newsViewModel.getAllSavedNews()
        newsViewModel.newsData.removeObservers(this)
        newsViewModel.saveNewsLiveData.observe(this) {savedNews->
            binding.rvNews.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = savedNewsAdapter
                savedNewsAdapter.submitList(savedNews)
            }
        }
    }
}