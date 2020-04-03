package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicoForm {

    @NotNull(message = "Título não pode ser nulo")
    @NotEmpty //Utilizando mensagem padrão (não pode estar vazio)
    @Size(max = 50, message = "Tamanho do Título maior que 50 caracteres")
    private String titulo;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 200)
    private String mensagem;

    @NotNull
    @NotEmpty
    private String cursoNome;

    public TopicoForm() {
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

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public Topico toEntity(Curso curso) {
        return new Topico(titulo, mensagem, curso);
    }
}
