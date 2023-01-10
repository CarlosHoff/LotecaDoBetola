package br.com.hoffmann.loteca.repository;

import br.com.hoffmann.loteca.domain.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findPessoaByCpf(String cpf);

    Optional<Usuario> findPessoaByEmail(String email);

    Optional<Usuario> findPessoaByTelefone(String telefone);

    Optional<Usuario> findPessoaByCelular(String celular);
}