package br.com.api.Forum.repository

import br.com.api.Forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {

}