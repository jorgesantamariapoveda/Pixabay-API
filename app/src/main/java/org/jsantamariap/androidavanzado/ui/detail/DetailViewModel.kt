package org.jsantamariap.androidavanzado.ui.detail

import androidx.lifecycle.ViewModel
import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.model.GhibliResponse
import org.jsantamariap.androidavanzado.repository.network.ApodService
import org.jsantamariap.androidavanzado.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//! Lógica de negocio, donde se hacen las peticiones a los servicios
class DetailViewModel : ViewModel() {

    fun getApod(apiKey: String, cb: ApodService.CallbackResponse<List<GhibliResponse>>) {

        ApodService().apodApi.getApod().enqueue(object : Callback<List<GhibliResponse>> {
            override fun onFailure(call: Call<List<GhibliResponse>>, t: Throwable) {
                cb.onFailure(t)
            }

            override fun onResponse(
                call: Call<List<GhibliResponse>>,
                response: Response<List<GhibliResponse>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }
/*
            override fun onResponse(call: Call<ApodResponse>, response: Response<ApodResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<ApodResponse>, t: Throwable) {
                cb.onFailure(t)
            }

 */

        })
    }

}
