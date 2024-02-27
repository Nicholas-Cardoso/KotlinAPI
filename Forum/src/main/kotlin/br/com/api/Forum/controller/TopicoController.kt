package br.com.api.Forum.controller

import br.com.api.Forum.dto.TopicoByCategoriaDto
import br.com.api.Forum.dto.TopicoForm
import br.com.api.Forum.dto.TopicoView
import br.com.api.Forum.dto.UpdateTopicoForm
import br.com.api.Forum.service.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun listTopico(
            @RequestParam(required = false) cursoName: String?,
            pagination: Pageable
            ): Page<TopicoView> {
        return service.listTopico(cursoName, pagination)
    }

    @GetMapping("/{id}")
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun findById(@PathVariable id: Long): TopicoView {
        return service.findById(id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoByCategoriaDto> {
        return service.relatorio()
    }

    @Transactional
    @PostMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun createTopico(
            @RequestBody @Valid dtoTopico: TopicoForm,
            uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topico = service.createTopico(dtoTopico)
        val uri = uriBuilder.path("topicos/${topico.id}").build().toUri()

        return ResponseEntity.created(uri).body(topico)
    }

    @Transactional
    @PutMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun updateTopico(@RequestBody @Valid dtoTopico: UpdateTopicoForm): ResponseEntity<TopicoView> {
        val topico = service.updateTopico(dtoTopico)

        return ResponseEntity.ok(topico)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deleteTopico(@PathVariable id: Long) {
        service.delete(id)
    }
}