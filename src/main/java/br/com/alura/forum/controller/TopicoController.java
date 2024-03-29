package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDetalheDTO;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.form.TopicoUpdateForm;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursorepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Cacheable(value = "topicos-todos-paginado")
    public Page<TopicoDTO> lista(
            @RequestParam(required = false) String nomeCurso,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {

        if (nomeCurso == null) {
            return TopicoDTO.toList(topicoRepository.findAll(paginacao));
        }

        return TopicoDTO.toList(topicoRepository
                .findByCursoNomeContainingIgnoreCase(nomeCurso, paginacao));
    }

    @GetMapping("/{id}")
    public TopicoDetalheDTO detalhar(@PathVariable Long id){
        return new TopicoDetalheDTO(topicoRepository.getOne(id));
    }

    @Transactional
    @PostMapping
    @CacheEvict(cacheNames = "topicos-todos-paginado", allEntries = true)
    public ResponseEntity<TopicoDTO> salvar(
            @RequestBody @Valid TopicoForm form,
            UriComponentsBuilder uriBuilder){

        Curso curso = cursorepository.findByNome(form.getCursoNome());
        Topico topico = form.toEntity(curso);

        Usuario usuario = usuarioRepository
                .findById(1L)
                .orElse(null);

        topico.setAutor(usuario);

        topicoRepository.save(topico);

        URI uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid  TopicoUpdateForm form){
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDTO(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "topicos-todos-paginado", allEntries = true)
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        if (isTopicoNotFound(id)){
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private boolean isTopicoNotFound(@PathVariable Long id) {
        return  !topicoRepository.findById(id).isPresent();
    }

}
