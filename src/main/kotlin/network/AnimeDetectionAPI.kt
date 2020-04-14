package network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AnimeDetectionAPI {
    val client by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TraceMeAPI.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        //create retrofit client
        return@lazy retrofit.create(TraceMeAPI::class.java)
    }
}