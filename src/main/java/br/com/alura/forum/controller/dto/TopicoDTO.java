package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO {

    private Long id;
    private String mensagem;
    private String titulo;
    private LocalDateTime dataCricao;

    public TopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.mensagem = topico.getMensagem();
        this.titulo = topico.getTitulo();
        this.dataCricao = topico.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCricao() {
        return dataCricao;
    }

    public static List<TopicoDTO> toList(List<Topico> topicos){
        return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
    }

}
