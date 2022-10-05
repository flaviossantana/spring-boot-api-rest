package br.com.alura.forum.repository;

import br.com.alura.forum.model.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoRepositoryTest {

    private static final String CURSO_HTML_5 = "HTML 5";

    @Autowired
    private CursoRepository repository;

    private void criarCursoHtml5(){
        this.repository.save(new Curso(CURSO_HTML_5, "Programação"));
    }

    @Test
    public void deveriaBuscarCursoPorNome() {

        criarCursoHtml5();

        Curso curso = this.repository.findByNome(CURSO_HTML_5);
        Assert.assertNotNull(curso);
        Assert.assertEquals(CURSO_HTML_5, curso.getNome());
        Assert.assertNotNull(curso.getCategoria());
        Assert.assertNotNull(curso.getId());
    }

    @Test
    public void deveriaNaoEncontrarCursoPorNome() {
        String nomeCurso = "HTML 5.1";
        Curso curso = this.repository.findByNome(nomeCurso);
        Assert.assertNull(curso);
    }

}
