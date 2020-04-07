package br.com.alura.forum.controller.validation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorMessageDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String campo;
    private String mensagem;

    public ErrorMessageDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
