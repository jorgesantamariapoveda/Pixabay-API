package org.jsantamariap.androidavanzado.repository.network

import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.model.GhibliResponse
import org.jsantamariap.androidavanzado.utils.Common
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApodApi {

    @GET(Common.PATH_GHIBLI)
    @Headers("Content-Type: application/json")
    fun getApod(): Call<List<GhibliResponse>>
}