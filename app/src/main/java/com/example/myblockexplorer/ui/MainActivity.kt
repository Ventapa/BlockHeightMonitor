package com.example.myblockexplorer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myblockexplorer.databinding.ActivityMainBinding
import com.example.myblockexplorer.ui.viewmodel.BlockchainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BlockchainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Using ViewBinding (requires "buildFeatures { viewBinding = true }" in module build.gradle
        // or you can use setContentView(R.layout.activity_main) + findViewById.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe the blockHeight
        viewModel.blockHeight.observe(this, Observer { height ->
            binding.textBlockHeight.text = "Block Height: $height"
        })

        // Observe the error message
        viewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                binding.textBlockHeight.text = it
            }
        })

        // Trigger a fetch
        viewModel.getBlockHeight()
    }
}
