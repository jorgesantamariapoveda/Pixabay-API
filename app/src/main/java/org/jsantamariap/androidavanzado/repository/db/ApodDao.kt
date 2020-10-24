package org.jsantamariap.androidavanzado.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.jsantamariap.androidavanzado.repository.model.ApodResponse

// Es como la API del servicio pero para las Entities
@Dao
abstract class ApodDao {

    // interesante lo de onConflict = OnConflictStrategy
    // Usado para el botón save
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertApod(apodResponse: ApodResponse)

    // Usado para cargar los datos en el mainFragment
    // aqui se le añade el LiveData y es donde tiene sentido
    // lo del ViewModel con el objeto observer
    @Query("SELECT * FROM apod_table")
    abstract fun getAllApod(): LiveData<List<ApodResponse>>
}