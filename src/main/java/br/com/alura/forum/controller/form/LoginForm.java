package br.com.alura.forum.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }


}
