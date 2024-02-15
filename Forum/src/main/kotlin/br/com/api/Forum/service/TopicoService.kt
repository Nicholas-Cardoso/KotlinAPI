package br.com.api.Forum.service


import br.com.api.Forum.dto.TopicoByCategoriaDto
import br.com.api.Forum.dto.TopicoForm
import br.com.api.Forum.dto.TopicoView
import br.com.api.Forum.dto.UpdateTopicoForm
import br.com.api.Forum.exception.NotFoundException
import br.com.api.Forum.mapper.TopicoFormMapper
import br.com.api.Forum.mapper.TopicoViewMapper
import br.com.api.Forum.model.Topico
import br.com.api.Forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private val repository: TopicoRepository,
        private val topicoMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Tópico não encontrado!"
) {
    fun listTopico(
            cursoName: String?,
            pagination: Pageable
            ): Page<TopicoView> {
        val topicos = if (cursoName != null) {
            repository.findByCursoNome(cursoName, pagination)
        } else {
            repository.findAll(pagination)
        }

        return topicos.map {
            t -> topicoMapper.map(t)
        }
    }

    fun findById(id: Long): TopicoView {
        val topico = findTopicoById(id)
        return topicoMapper.map(topico)
    }

    fun relatorio(): List<TopicoByCategoriaDto> {
        return repository.relatorio()
    }

    fun createTopico(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoMapper.map(topico)
    }

    fun updateTopico(updateForm: UpdateTopicoForm): TopicoView {
        val topico = findTopicoById(updateForm.id)

        topico.titulo = updateForm.titulo
        topico.mensagem = updateForm.mensagem
        return topicoMapper.map(topico)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun findTopicoById(id: Long): Topico {
        return repository.findById(id)
                .orElseThrow{
                    NotFoundException(notFoundMessage)
                }
    }
}