package com.agri.connect.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agri.connect.databinding.ActivityMainBinding
import com.agri.connect.ui.crop.CropListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // default fragment
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, CropListFragment.newInstance())
            .commit()
    }
}
