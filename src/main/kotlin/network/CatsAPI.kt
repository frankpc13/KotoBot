package network

import kotlinx.coroutines.Deferred
import models.DataResponseCats
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface CatsAPI {
    companion object {
        const val API_BASE_URL = "https://aws.random.cat/"
    }

    @GET("meow")
    fun getRandomCatAsync() : Deferred<DataResponseCats>
}