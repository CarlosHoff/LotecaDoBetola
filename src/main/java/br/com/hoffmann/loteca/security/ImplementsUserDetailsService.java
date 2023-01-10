package br.com.hoffmann.loteca.security;

import br.com.hoffmann.loteca.domain.entitys.Usuario;
import br.com.hoffmann.loteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails retorno = null;
        Optional<Usuario> user = this.usuarioRepository.findOne(Example.of(Usuario.from(username)));
        if (user.isPresent()) {
            Usuario usuario = user.get();
            retorno = new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
            return retorno;
        }
        return retorno;
    }
}
