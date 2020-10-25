package org.jsantamariap.androidavanzado.repository.network

import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.model.PixabayResponse
import org.jsantamariap.androidavanzado.utils.Common
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApodApi {

    @GET(Common.PATH_NASA_APOD)
    @Headers("Content-Type: application/json")
    fun getApod(@Query("api_key") apiKey: String): Call<ApodResponse>

    @GET(Common.PATH_PIXABAY)
    @Headers("Content-Type: application/json")
    fun getPixabay(
        @Query("key") key: String,
        @Query("per_page") perPage: String,
        @Query("category") category: String
    ): Call<PixabayResponse>
}