package br.com.api.Forum.dto

data class TopicoDTO(
        val titulo: String,
        val mensagem: String,
        val idCurso: Long,
        val idAutor: Long
)
