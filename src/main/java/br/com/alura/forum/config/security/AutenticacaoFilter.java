package br.com.alura.forum.config.security;

import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    public AutenticacaoFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);

        if (tokenService.isValido(token)) {
            usuarioRepository.findById(tokenService.getIdUsuario(token))
                    .ifPresent(usuario -> {
                                SecurityContextHolder
                                        .getContext()
                                        .setAuthentication(new UsernamePasswordAuthenticationToken(
                                                usuario,
                                                null,
                                                usuario.getAuthorities())
                                        );
                            }
                    );
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7);
    }


}
