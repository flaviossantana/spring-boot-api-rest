package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.Cursorepository;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.repository.UsuarioRepository;
import org.apache.tomcat.util.http.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private Cursorepository cursorepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<TopicoDTO> lista(String nomeCurso) {

        if (nomeCurso == null) {
            return TopicoDTO.toList(topicoRepository.findAll());
        }

        return TopicoDTO.toList(topicoRepository.findByCursoNomeContainingIgnoreCase(nomeCurso));
    }

    @PostMapping
    public void salvar(@RequestBody TopicoForm form){

        Curso curso = cursorepository.findByNome(form.getCursoNome());
        Topico topico = form.toEntity(curso);

        Usuario usuario = usuarioRepository
                .findById(1L)
                .orElse(null);

        topico.setAutor(usuario);

        topicoRepository.save(topico);

    }


}
