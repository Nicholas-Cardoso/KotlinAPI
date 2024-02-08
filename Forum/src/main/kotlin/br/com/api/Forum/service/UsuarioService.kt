package br.com.api.Forum.service

import br.com.api.Forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(private var usuarios: List<Usuario>) {
    init {
        val usuario = Usuario(
                id = 1,
                nome = "Ana da Silva",
                email = "ana@email.com.br"
        )
        val usuario2 = Usuario(
                id = 2,
                nome = "Ana da Silva2",
                email = "ana@email.com.br2"
        )
        usuarios = Arrays.asList(usuario, usuario2)
    }

    fun findById(id: Long): Usuario {
        return usuarios.stream().filter {
            u -> u.id == id
        }.findFirst().get()
    }
}
