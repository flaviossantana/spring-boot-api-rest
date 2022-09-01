package br.com.alura.forum.controller;

import br.com.alura.forum.controller.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {


    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm loginForm){

        System.out.println(loginForm.getEmail());
        System.out.println(loginForm.getSenha());

        return ResponseEntity.ok().build();
    }

}
