package br.com.alura.forum.form;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

public class TopicoForm {

    private String titulo;

    private String mensagem;

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
