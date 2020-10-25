package org.jsantamariap.androidavanzado.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import org.jsantamariap.androidavanzado.repository.db.PixabayRoomDatabase
import org.jsantamariap.androidavanzado.repository.model.ItemPixabay
import org.jsantamariap.androidavanzado.repository.model.PixabayResponse
import org.jsantamariap.androidavanzado.repository.network.PixabayService
import org.jsantamariap.androidavanzado.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Lógica de negocio, donde se hacen las peticiones a los servicios, ya sean externas (server) o
//internas (base de datos local, en este caso Room)

class DetailViewModel(private val context: Application) : ViewModel() {

    // MARK: - API

    fun getServerPixabay(cb: PixabayService.CallbackResponse<PixabayResponse>) {

        PixabayService().pixabayApi.getPixabay(Common.API_KEY_PIXABAY, Common.PER_PAGE_PIXABAY, Common.CATEGORY_PIXABAY)
            .enqueue(object : Callback<PixabayResponse> {

                override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
                    cb.onFailure(t)
                }

                override fun onResponse(
                    call: Call<PixabayResponse>,
                    response: Response<PixabayResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        cb.onResponse(response.body()!!)
                    } else {
                        cb.onFailure(Throwable(response.message()), response)
                    }
                }

            })
    }

    // MARK: - Room

    fun insertItemPixabay(itemPixabay: ItemPixabay) {
        PixabayRoomDatabase.getInstance(context).pixabayDao().insertItemPixabay(itemPixabay)
    }

    fun deleteItemPixabay(itemPixabay: ItemPixabay) {
        PixabayRoomDatabase.getInstance(context).pixabayDao().deleteItemPixabay(itemPixabay)
    }
}
