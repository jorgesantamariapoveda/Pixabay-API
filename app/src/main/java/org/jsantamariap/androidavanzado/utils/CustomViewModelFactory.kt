package org.jsantamariap.androidavanzado.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.jsantamariap.androidavanzado.ui.detail.DetailViewModel

class CustomViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel() as T
    }

}