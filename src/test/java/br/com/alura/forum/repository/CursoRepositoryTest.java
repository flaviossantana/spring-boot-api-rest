package br.com.alura.forum.repository;

import br.com.alura.forum.model.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    public void deveriaBuscarCursoPorNome() {
        String nomeCurso = "HTML 5";
        Curso curso = this.repository.findByNome(nomeCurso);
        Assert.assertNotNull(curso);
        Assert.assertEquals(curso.getNome(), nomeCurso);
    }

    @Test
    public void deveriaNaoEncontrarCursoPorNome() {
        String nomeCurso = "HTML 5.1";
        Curso curso = this.repository.findByNome(nomeCurso);
        Assert.assertNull(curso);
    }

}
