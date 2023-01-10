package br.com.hoffmann.loteca.domain.response;

import br.com.hoffmann.loteca.domain.entitys.Usuario;
import br.com.hoffmann.loteca.domain.enums.TipoDeUsuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class UsuarioResponse implements Serializable {

    private Long id;

    private String nome;

    private String cpf;

    private TipoDeUsuario tipoDeUsuario;

    private String celular;

    private String email;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getUsuarioID();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.tipoDeUsuario = usuario.getTipoDeUsuario();
        this.celular = usuario.getCelular();
        this.email = usuario.getEmail();
    }
}