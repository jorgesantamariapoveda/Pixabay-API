package org.jsantamariap.androidavanzado.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.jsantamariap.androidavanzado.repository.model.ItemPixabay

// El Dao viene a ser la API de la base de datos
@Dao
abstract class PixabayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItemPixabay(itemPixabay: ItemPixabay)

    // Usado para cargar los datos en el mainFragment
    // aqui se le añade el LiveData y es donde tiene sentido
    // lo del ViewModel con el objeto observer
    @Query("SELECT * FROM pixabay_table")
    abstract fun getAllItemsPixabay(): LiveData<List<ItemPixabay>>

    @Delete
    abstract fun deleteItemPixabay(itemPixabay: ItemPixabay)
}