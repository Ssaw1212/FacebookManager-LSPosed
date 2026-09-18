package com.ssaw1212.facebookmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssaw1212.facebookmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        binding.blockAds.isChecked = prefs.getBoolean("block_ads", true)
        binding.downloadVideo.isChecked = prefs.getBoolean("download_video", true)
        binding.blockAds.setOnCheckedChangeListener { _, checked -> prefs.edit().putBoolean("block_ads", checked).apply() }
        binding.downloadVideo.setOnCheckedChangeListener { _, checked -> prefs.edit().putBoolean("download_video", checked).apply() }
    }
}
