package com.example.myblockexplorer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myblockexplorer.data.repository.BlockchainRepository

class BlockchainViewModel : ViewModel() {

    private val repository = BlockchainRepository()

    private val _blockHeight = MutableLiveData<String>()
    val blockHeight: LiveData<String> get() = _blockHeight

    // Optionally track errors
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    /**
     * Fetch the current block height from the repository.
     */
    fun getBlockHeight() {
        repository.fetchBlockHeight { result ->
            if (result != null) {
                _blockHeight.postValue(result)
            } else {
                _errorMessage.postValue("Failed to load block height.")
            }
        }
    }
}
