package org.jsantamariap.androidavanzado.repository.network

import okhttp3.OkHttpClient
import org.jsantamariap.androidavanzado.utils.Common
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApodService {

    interface CallbackResponse<T> {
        fun onResponse(response: T)
        fun onFailure(t: Throwable, response: Response<*>? = null)
    }

    val apodApi: ApodApi

    init {
        val timeout: Long = 6 * 1000
        val client = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Common.BASE_URL_GHIBLI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apodApi = retrofit.create(ApodApi::class.java)
    }
}
