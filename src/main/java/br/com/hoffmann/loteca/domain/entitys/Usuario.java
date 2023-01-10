package br.com.hoffmann.loteca.domain.entitys;

import br.com.hoffmann.loteca.domain.enums.TipoDeUsuario;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name = "USUARIO")
public class Usuario implements UserDetails {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(sequenceName = "SQ_USUARIO", allocationSize = 1, name = "SQ_USUARIO")
    @Column(name = "ID")
    private Long usuarioID;

    @Column(length = 50, name = "NOME")
    private String nome;

    @Column(length = 14, name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "SENHA")
    private String senha;

    @Column(length = 1, name = "TIPO_DE_USUARIO")
    private TipoDeUsuario tipoDeUsuario;

    @Column(length = 11, name = "CELULAR")
    private String celular;

    @Column(length = 100, name = "EMAIL")
    private String email;

    private Usuario(String email) {
        this.email = email;
        usuarioID = null;
        senha = null;
    }

    public Usuario() {
    }

    public static Usuario from(String email) {
        return new Usuario(email);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
