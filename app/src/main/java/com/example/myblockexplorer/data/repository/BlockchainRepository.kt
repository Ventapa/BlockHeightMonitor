package com.example.myblockexplorer.data.repository

import android.util.Log
import com.example.myblockexplorer.data.api.BlockstreamService
import retrofit2.*
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BlockchainRepository {

    // Build Retrofit instance
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://blockstream.info/api/")
        // Scalars first for plain-text responses
        .addConverterFactory(ScalarsConverterFactory.create())
        // Gson next for JSON endpoints (if you use them)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: BlockstreamService = retrofit.create(BlockstreamService::class.java)

    /**
     * Fetch the current block height as a plain-text string.
     * The callback onResult returns the block height or null on failure.
     */
    fun fetchBlockHeight(onResult: (String?) -> Unit) {
        val call = service.getCurrentBlockHeight()
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    Log.e("BlockchainRepository", "Response error: ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("BlockchainRepository", "Request failed", t)
                onResult(null)
            }
        })
    }
}
