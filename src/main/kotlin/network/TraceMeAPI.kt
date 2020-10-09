package network

import kotlinx.coroutines.Deferred
import models.DataResponseTraceMe
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface TraceMeAPI {
    companion object {
        const val API_BASE_URL = "https://trace.moe/api/"
    }

    @Multipart
    @POST("search")
    fun getRelatedAnimeByImage(@Part image:MultipartBody.Part) : Deferred<ResponseBody>

}