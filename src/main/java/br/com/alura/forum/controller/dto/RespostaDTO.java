package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Resposta;

import java.time.LocalDateTime;

public class RespostaDTO {

	private Long id;

	private String mensagem;

	private LocalDateTime dataCriacao;

	private Boolean solucao;

	private String autor;

	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.solucao = resposta.getSolucao();
		this.autor = resposta.getAutor().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Boolean getSolucao() {
		return solucao;
	}

	public String getAutor() {
		return autor;
	}
}
