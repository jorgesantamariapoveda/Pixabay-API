package org.jsantamariap.androidavanzado.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.jsantamariap.androidavanzado.ui.detail.DetailViewModel
import org.jsantamariap.androidavanzado.ui.main.MainFragmentViewModel

class CustomViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MainFragmentViewModel::class.java) -> MainFragmentViewModel(application)
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(application)
                else -> throw IllegalArgumentException()
            }
        } as T

        // Antes de tener necesidad de dos viewModel, que si se queda como uno lo pueda dejar
        // así
        // return DetailViewModel(application) as T
    }

}