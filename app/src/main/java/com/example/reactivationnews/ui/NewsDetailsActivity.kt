package com.example.reactivationnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reactivationnews.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra("web_url")?.let {
            binding.webViewNews.loadUrl(it)
        }
    }
}