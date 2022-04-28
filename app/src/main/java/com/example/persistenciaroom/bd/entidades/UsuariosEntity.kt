package com.example.persistenciaroom.bd.entidades

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "TblUsuarios")
data class UsuariosEntity(
    @PrimaryKey(autoGenerate = true)
    val idUsuario: Int,
    @ColumnInfo(name = "nombreUsuario")
    val nombreUsuario: String,
    @ColumnInfo(name = "passUsuario")
    val passUsuario: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "nombres")
    val nombres: String,
    @ColumnInfo(name = "apellidos")
    val apellidos: String
): Parcelable