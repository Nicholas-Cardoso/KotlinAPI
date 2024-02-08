package br.com.api.Forum.service

import br.com.api.Forum.dto.TopicoForm
import br.com.api.Forum.dto.TopicoView
import br.com.api.Forum.dto.UpdateTopicoForm
import br.com.api.Forum.exception.NotFoundException
import br.com.api.Forum.mapper.TopicoFormMapper
import br.com.api.Forum.mapper.TopicoViewMapper
import br.com.api.Forum.model.Curso
import br.com.api.Forum.model.Topico
import br.com.api.Forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
        private var topicos: List<Topico>,
        private val topicoMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Tópico não encontrado!"
) {

    init {
        val topico = Topico(
                id = 1,
                titulo = "Duvida Kotlin",
                mensagem = "Variaveis no Kotlin",
                curso = Curso(
                        id = 1,
                        nome = "Kotlin",
                        categoria = "Programacao"
                ),
                autor = Usuario(
                        id = 1,
                        nome = "Ana da Silva",
                        email = "ana@email.com"
                )
        )
        val topico2 = Topico(
                id = 2,
                titulo = "Duvida Kotlin2",
                mensagem = "Variaveis no Kotlin2",
                curso = Curso(
                        id = 2,
                        nome = "Kotlin2",
                        categoria = "Programacao2"
                ),
                autor = Usuario(
                        id = 2,
                        nome = "Ana da Silva2",
                        email = "ana@email.com2"
                )
        )
        val topico3 = Topico(
                id = 3,
                titulo = "Duvida Kotlin3",
                mensagem = "Variaveis no Kotlin3",
                curso = Curso(
                        id = 3,
                        nome = "Kotlin3",
                        categoria = "Programacao3"
                ),
                autor = Usuario(
                        id = 3,
                        nome = "Ana da Silva3",
                        email = "ana@email.com3"
                )
        )

        topicos = Arrays.asList(topico, topico2, topico3)
    }

    fun listTopico(): List<TopicoView> {
        return topicos.stream().map { t ->
            topicoMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun findById(id: Long): TopicoView {
        val topico = findTopicoById(id)
        return topicoMapper.map(topico)
    }

    fun createTopico(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topicoMapper.map(topico)
    }

    fun updateTopico(updateForm: UpdateTopicoForm): TopicoView {
        val topico = findTopicoById(updateForm.id)
        val updatedTopico = Topico(
                id = topico.id,
                titulo = updateForm.titulo,
                mensagem = updateForm.mensagem,
                dataCriacao = topico.dataCriacao,
                curso = topico.curso,
                autor = topico.autor,
                status = topico.status,
                respostas = topico.respostas
        )

        topicos = topicos.minus(topico).plus(updatedTopico)
        return topicoMapper.map(updatedTopico)
    }

    fun delete(id: Long) {
        val topico = findTopicoById(id)

        topicos = topicos.minus(topico)
    }

    fun findTopicoById(id: Long): Topico {
        return topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow {
            NotFoundException(notFoundMessage)
        }
    }
}