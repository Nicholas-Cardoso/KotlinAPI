package br.com.api.Forum.controller

import br.com.api.Forum.dto.TopicoDTO
import br.com.api.Forum.dto.TopicoForm
import br.com.api.Forum.dto.TopicoView
import br.com.api.Forum.dto.UpdateTopicoForm
import br.com.api.Forum.model.Topico
import br.com.api.Forum.service.TopicoService
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listTopico(): List<TopicoView>{
        return service.listTopico()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicoView {
        return service.findById(id)
    }

    @PostMapping
    fun createTopico(
            @RequestBody @Valid dtoTopico: TopicoForm,
            uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topico = service.createTopico(dtoTopico)
        val uri = uriBuilder.path("topicos/${topico.id}").build().toUri()

        return ResponseEntity.created(uri).body(topico)
    }

    @PutMapping
    fun updateTopico(@RequestBody @Valid dtoTopico: UpdateTopicoForm): ResponseEntity<TopicoView> {
        val topico = service.updateTopico(dtoTopico)

        return ResponseEntity.ok(topico)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTopico(@PathVariable id: Long) {
        service.delete(id)
    }
}