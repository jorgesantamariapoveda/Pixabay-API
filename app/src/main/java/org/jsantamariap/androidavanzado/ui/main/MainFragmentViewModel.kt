package org.jsantamariap.androidavanzado.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jsantamariap.androidavanzado.repository.db.ApodRoomDatabase
import org.jsantamariap.androidavanzado.repository.model.ApodResponse

//! Precisamente por este pienso que la detalle no necesita
// que herede de viewModel, o si dependeiendo de si la vista
// detail se usa para lo del boton que cambia de icono
// dependeiendo de si es local o base de datos
// en la vista principal si que me interesa por el tema de LiveData
// para que se refresca "automaticamente". en cambio en la de detalle me
// da igual. Seguir viendo videos instructor para decidir que hacer
class MainFragmentViewModel(private val context: Application): ViewModel() {

    fun getLocalAllApod(): LiveData<List<ApodResponse>> {
        return ApodRoomDatabase.getInstance(context).apodDao().getAllApod()
    }

}