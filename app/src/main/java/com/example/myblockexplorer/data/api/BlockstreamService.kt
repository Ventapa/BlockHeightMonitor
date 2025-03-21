package com.example.myblockexplorer.data.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * Defines the endpoints for the Blockstream API.
 * For example, to retrieve the current tip (latest block) height:
 */
interface BlockstreamService {

    @GET("blocks/tip/height")
    fun getCurrentBlockHeight(): Call<String>

    // You can add more endpoints, for example:
    // @GET("blocks/tip/hash")
    // fun getCurrentBlockHash(): Call<String>
}
