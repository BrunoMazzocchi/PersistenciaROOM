package com.example.persistenciaroom.bd.repository

import androidx.lifecycle.LiveData
import com.example.persistenciaroom.bd.dao.UsuariosDao
import com.example.persistenciaroom.bd.entidades.UsuariosEntity

class UsuarioRepository(private val dao: UsuariosDao) {
    val listado: LiveData<List<UsuariosEntity>> =
        dao.getAllRealData()

    suspend fun addUsuario(usuario: UsuariosEntity) {
        dao.insert(usuario)
    }

    suspend fun updateUsuario(usuario: UsuariosEntity) {
        dao.update(usuario)
    }

    suspend fun deleteUsuario(usuario: UsuariosEntity) {
        dao.delete(usuario)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
