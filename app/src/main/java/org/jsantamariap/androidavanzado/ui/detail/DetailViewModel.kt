package org.jsantamariap.androidavanzado.ui.detail

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import org.jsantamariap.androidavanzado.repository.db.ApodRoomDatabase
import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.model.GhibliResponse
import org.jsantamariap.androidavanzado.repository.network.ApodService
import org.jsantamariap.androidavanzado.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//! Lógica de negocio, donde se hacen las peticiones a los servicios
class DetailViewModel(private val context: Application) : ViewModel() {

    fun getApod(apiKey: String, cb: ApodService.CallbackResponse<ApodResponse>) {

        ApodService().apodApi.getApod(apiKey).enqueue(
            object : Callback<ApodResponse> {

                override fun onFailure(call: Call<ApodResponse>, t: Throwable) {
                    cb.onFailure(t)
                }

                override fun onResponse(
                    call: Call<ApodResponse>,
                    response: Response<ApodResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        cb.onResponse(response.body()!!)
                    } else {
                        cb.onFailure(Throwable(response.message()), response)
                    }
                }

            })
    }

    fun insertApodToRoomDatabase(apodResponse: ApodResponse) {
        ApodRoomDatabase.getInstance(context).apodDao().insertApod(apodResponse)
    }

}
