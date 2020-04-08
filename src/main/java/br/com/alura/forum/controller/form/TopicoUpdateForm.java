package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicoUpdateForm {

    @NotNull(message = "Título não pode ser nulo")
    @NotEmpty
    @Size(max = 50, message = "Tamanho do Título maior que 50 caracteres")
    private String titulo;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 200)
    private String mensagem;

    public TopicoUpdateForm() {
        super();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(titulo);
        topico.setMensagem(mensagem);
        return topico;
    }
}
