package org.jsantamariap.androidavanzado.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import org.jsantamariap.androidavanzado.repository.model.ApodResponse

// Es como la API del servicio pero para las Entities
@Dao
abstract class ApodDao {

    // interesante lo de onConflict = OnConflictStrategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertApod(apodResponse: ApodResponse)

    //@Query("SELECT * FROM apod_table")
}