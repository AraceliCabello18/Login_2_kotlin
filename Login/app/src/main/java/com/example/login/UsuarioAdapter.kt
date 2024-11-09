package com.example.login

import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class UsuarioAdapter (
    private val listaUsuarios: MutableList<Usuarios>,
    private val onBorrarClick: (usuarios) -> Unit,
    private val onEditarClick: (usuarios) -> Unit

): RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>(){
    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        int tvUsuario: TextView = itemView.findViewById(R.id.tvIdUsuario)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvEdad: TextView = itemView.findViewById(R.id.tvEdad)
        val btnEditar Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar Button = itemView.findViewById(R.id.btnEliminar)

        fun bind(usuario: Usuarios){
            tvIdUsuario.text = Usuarios.id_usuario.toString()
            tvNombre.text = Usuarios.nombre
            tvEdad.text = Usuarios.edad

            btnEditar.setOnClickListener{
                on EditarClick(usuarios)
            }
            btnEliminar.setOnClickListener{
                on BorrarClick(usuarios)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
       val view = layoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario,parent,false)
        return UsuarioViewHolder(view)
    }
    override fun onBindViewHolder(holder: UsuarioViewHolder, position:Int) {
     holder.bind(listaUsuarios[position])
    }

    override fun getItemCount(): Int = listaUsuarios.size {
        TODO("Not yet implemented")
    }
}