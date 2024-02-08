package br.com.api.Forum.mapper

import br.com.api.Forum.dto.TopicoForm
import br.com.api.Forum.model.Topico
import br.com.api.Forum.service.CursoService
import br.com.api.Forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
        private val cursoService: CursoService,
        private val usuarioService: UsuarioService
) : Mapper<TopicoForm, Topico> {
    override fun map(t: TopicoForm): Topico {
        return Topico(
                titulo = t.titulo,
                mensagem = t.mensagem,
                curso = cursoService.findById(t.idCurso),
                autor = usuarioService.findById(t.idAutor)
        )
    }
}
