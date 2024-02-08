package br.com.api.Forum.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicoForm(
        @field:NotBlank
        @field:Size(min = 5, max = 100)
        val titulo: String,

        @field:NotBlank
        val mensagem: String,

        @field:NotNull
        val idCurso: Long,

        @field:NotNull
        val idAutor: Long
)