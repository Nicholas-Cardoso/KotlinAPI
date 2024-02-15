package br.com.api.Forum.repository

import br.com.api.Forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {

}