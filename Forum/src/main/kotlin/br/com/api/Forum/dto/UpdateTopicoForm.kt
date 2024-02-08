package br.com.api.Forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateTopicoForm(
        @field:NotNull
        val id: Long,
        @field:NotEmpty
        @Size(min = 5, max = 10)
        val titulo: String,
        @field:NotEmpty
        val mensagem: String
)
