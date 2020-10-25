package org.jsantamariap.androidavanzado.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.jsantamariap.androidavanzado.repository.model.ItemPixabay

@Database(entities = [ItemPixabay::class], version = 1, exportSchema = false)
abstract class PixabayRoomDatabase: RoomDatabase() {

    abstract fun pixabayDao(): PixabayDao

    //! Mediante el getInstance equivale a un Singleton
    companion object {

        private var instance: PixabayRoomDatabase? = null

        fun getInstance(context: Context): PixabayRoomDatabase {
            if (instance == null) {
                synchronized(PixabayRoomDatabase::class) {
                    //.allowMainThreadQueries() con esto se permiten llamadas a la bd desde el hilo principal,
                    //sino en las llamadas habría que especificarlo

                    //.fallbackToDestructiveMigration(), cuando se sube la versión si no encuentra el control
                    //de versión borra todos los datos almacenados en local
                    instance = Room.databaseBuilder(context, PixabayRoomDatabase::class.java, "pixabay_table")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance!!
        }
    }
}