package com.example.persistenciaroom.bd.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.persistenciaroom.bd.dao.MainBaseDatos
import com.example.persistenciaroom.bd.entidades.UsuariosEntity
import com.example.persistenciaroom.bd.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModels(application:
                        Application
): AndroidViewModel(application) {
    val lista : LiveData<List<UsuariosEntity>>
    private val repository: UsuarioRepository
    init {
        val usuarioDao =
            MainBaseDatos.getDataBase(application).usuariosDao()
        repository = UsuarioRepository(usuarioDao)
        lista = repository.listado
    }
    fun agregarUsuario(usuario: UsuariosEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUsuario(usuario)
        }
    }
    fun actualizarUsuario(usuario: UsuariosEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUsuario(usuario)
        }
    }
    fun eliminarUsuario(usuario: UsuariosEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUsuario(usuario)
        }
    }
    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}

