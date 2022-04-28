package com.example.persistenciaroom.fragments.actualizar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.persistenciaroom.R
import com.example.persistenciaroom.bd.entidades.UsuariosEntity
import com.example.persistenciaroom.bd.viewmodels.UsuarioViewModels
import com.example.persistenciaroom.databinding.FragmentActualizarBinding

class ActualizarFragment : Fragment() {
    lateinit var fBinding: FragmentActualizarBinding
    private val args by navArgs<ActualizarFragmentArgs>()
    private lateinit var viewModel: UsuarioViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fBinding =
            FragmentActualizarBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(UsuarioViewModels::class.java)
        with(fBinding) {

            TxtUserName.setText(args.currentUsuario.nombreUsuario)
            TxtPassword.setText(args.currentUsuario.passUsuario)
            TxtEmail.setText(args.currentUsuario.email)
            TxtNombres.setText(args.currentUsuario.nombres)
            TxtApellidos.setText(args.currentUsuario.apellidos)
            BtnActualizar.setOnClickListener {
                GuardarCambios()
            }
        }
        //Agregar menu
        setHasOptionsMenu(true)
        return fBinding.root
    }
    private fun GuardarCambios() {
        val userName = fBinding.TxtUserName.text.toString()
        val pass = fBinding.TxtPassword.text.toString()
        val nombres = fBinding.TxtNombres.text.toString()
        val apellidos = fBinding.TxtApellidos.text.toString()
        val email = fBinding.TxtEmail.text.toString()
        //Crear el objeto
        val user =
            UsuariosEntity(args.currentUsuario.idUsuario,
                userName, pass, email, nombres, apellidos)
        //Actualizar
        viewModel.actualizarUsuario(user)
        Toast.makeText(requireContext(), "Registro actualizado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.actualizar_Lista)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater
    ) {
        inflater.inflate(R.menu.delete_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.mnuEliminar) {
            eliminarUsuario()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun eliminarUsuario() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarUsuario(args.currentUsuario)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.actualizar_Lista)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.currentUsuario.nombreUsuario}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.currentUsuario.nombreUsuario}?")
        alerta.create().show()
    }
}