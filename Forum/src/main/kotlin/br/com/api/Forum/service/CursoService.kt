package br.com.api.Forum.service

import br.com.api.Forum.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(
        private var cursos: List<Curso>
) {
    init {
        val curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Desenvolvimento de Software"
        )
        val curso2 = Curso(
                id = 2,
                nome = "CSharp",
                categoria = "Desenvolvimento de Software"
        )
        cursos = Arrays.asList(curso, curso2)
    }

    fun findById(id: Long): Curso {
        return cursos.stream().filter {
            c -> c.id == id
        }.findFirst().get()
    }
}
