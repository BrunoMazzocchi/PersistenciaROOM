package com.example.persistenciaroom.bd.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.persistenciaroom.bd.entidades.UsuariosEntity

interface MainDataBaseProvider {
    fun usuariosDao(): UsuariosDao
}
@Database(
    entities = [UsuariosEntity::class],
    version = 1
)
abstract class MainBaseDatos : RoomDatabase(),
    MainDataBaseProvider {
    abstract override fun usuariosDao(): UsuariosDao
    companion object {
        @Volatile
        private var INSTANCE: MainBaseDatos? = null
        fun getDataBase(context: Context): MainBaseDatos {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainBaseDatos::class.java,
                        "usuario_main_db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

