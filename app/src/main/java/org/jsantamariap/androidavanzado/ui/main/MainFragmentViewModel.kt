package org.jsantamariap.androidavanzado.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jsantamariap.androidavanzado.repository.db.PixabayRoomDatabase
import org.jsantamariap.androidavanzado.repository.model.ItemPixabay

class MainFragmentViewModel(private val context: Application): ViewModel() {

    fun getAllItemsPixabay(): LiveData<List<ItemPixabay>> {
        return PixabayRoomDatabase.getInstance(context).pixabayDao().getAllItemsPixabay()
    }
}