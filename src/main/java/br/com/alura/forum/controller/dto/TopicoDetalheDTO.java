package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDetalheDTO {

    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusTopico status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String autor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String curso;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<RespostaDTO> respostas = new ArrayList<>();

    public TopicoDetalheDTO(Topico topico) {
        this.id = topico.getId();
        this.mensagem = topico.getMensagem();
        this.titulo = topico.getTitulo();
        this.dataCriacao = topico.getDataCriacao();
        this.respostas.addAll(topico.getRespostas()
                .stream()
                .map(RespostaDTO::new)
                .collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }
}
