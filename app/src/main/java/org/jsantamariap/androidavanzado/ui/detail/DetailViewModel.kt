package org.jsantamariap.androidavanzado.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import org.jsantamariap.androidavanzado.repository.db.ApodRoomDatabase
import org.jsantamariap.androidavanzado.repository.model.ApodResponse
import org.jsantamariap.androidavanzado.repository.network.ApodService
import org.jsantamariap.androidavanzado.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//! Lógica de negocio, donde se hacen las peticiones a los servicios
class DetailViewModel(private val context: Application) : ViewModel() {

    fun getServerApod(cb: ApodService.CallbackResponse<ApodResponse>) {

        ApodService().apodApi.getApod(Common.API_KEY_NASA_APOD).enqueue(
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

    fun insertApod(apodResponse: ApodResponse) {
        ApodRoomDatabase.getInstance(context).apodDao().insertApod(apodResponse)
    }

    fun deleteApod(apodResponse: ApodResponse) {
        ApodRoomDatabase.getInstance(context).apodDao().deleteApod(apodResponse)
    }

    fun getLocalApod(apodId: String) : ApodResponse {
        return ApodRoomDatabase.getInstance(context).apodDao().getApod(apodId)
    }

}
