package br.com.api.Forum.dto

import br.com.api.Forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView(
        val id: Long?,
        val titulo: String,
        val mensagem: String,
        val status: StatusTopico,
        val dataCriacao: LocalDateTime,
)
