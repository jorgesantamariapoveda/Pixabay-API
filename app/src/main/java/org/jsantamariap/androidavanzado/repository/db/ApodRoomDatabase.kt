package org.jsantamariap.androidavanzado.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.jsantamariap.androidavanzado.repository.model.ApodResponse

@Database(entities = [ApodResponse::class], version = 1, exportSchema = false)
abstract class ApodRoomDatabase: RoomDatabase() {

    abstract fun apodDao(): ApodDao

    //! Mediante el getInstance equivale a un Singleton
    companion object {

        private var instance: ApodRoomDatabase? = null

        fun getInstance(context: Context): ApodRoomDatabase {
            if (instance == null) {
                synchronized(ApodRoomDatabase::class) {
                    //.allowMainThreadQueries() con esto se permiten llamadas a la bd desde el hilo principal,
                    //sino en las llamadas habría que expecificarlo
                    //.fallbackToDestructiveMigration(), cuando se sube la versión si no encuentra el control
                    //de versión borra todos los datos almacenados en local
                    instance = Room.databaseBuilder(context, ApodRoomDatabase::class.java, "apod_room_db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance!!
        }
    }
}