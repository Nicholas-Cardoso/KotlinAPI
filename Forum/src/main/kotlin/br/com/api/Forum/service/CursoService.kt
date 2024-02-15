package br.com.api.Forum.service

import br.com.api.Forum.exception.NotFoundException
import br.com.api.Forum.model.Curso
import br.com.api.Forum.repository.CursoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(
        private val repository: CursoRepository,
        private val notFoundMessage: String = "Curso não encontrado!"
) {
    fun findById(id: Long): Curso {
        return repository.findById(id)
                .orElseThrow{
                    NotFoundException(notFoundMessage)
                }
    }
}
