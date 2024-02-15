package br.com.api.Forum.repository

import br.com.api.Forum.dto.TopicoByCategoriaDto
import br.com.api.Forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {
    fun findByCursoNome(
            curso: String?,
            pagination: Pageable
            ): Page<Topico>

    @Query("select new br.com.api.Forum.dto.TopicoByCategoriaDto(curso.categoria, count(*)) from Topico t join t.curso curso group by curso.categoria")
    fun relatorio(): List<TopicoByCategoriaDto>
}