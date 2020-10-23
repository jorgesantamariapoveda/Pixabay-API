package org.jsantamariap.androidavanzado.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.jsantamariap.androidavanzado.ui.detail.DetailViewModel

class CustomViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(application) as T
    }

}