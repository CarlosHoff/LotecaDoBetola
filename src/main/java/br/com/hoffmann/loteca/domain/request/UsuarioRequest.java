package br.com.hoffmann.loteca.domain.request;

import br.com.hoffmann.loteca.domain.enums.TipoDeUsuario;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class UsuarioRequest implements Serializable {

    @NotNull
    @Size(min = 1, max = 50, message = "NOME deve estar entre 1 a 50 caracteres")
    private String nome;

    @NotNull
    private String cpf;

    private String senha;

    @NotNull
    private TipoDeUsuario tipoDeUsuario;

    @NotNull
    private Character status;

    @NotNull
    @Size(min = 1, max = 30, message = "ESTADO deve estar entre 1 e 30 caracteres")
    private String estado;

    @NotNull
    @Size(min = 2, max = 2, message = "SIGLA deve ter 2 caracteres")
    private String sigla;

    @NotNull
    @Size(min = 1, max = 50, message = "CIDADE deve estar entre 1 e 50 caracteres")
    private String cidade;

    @NotNull
    @Size(min = 1, max = 100, message = "RUA deve estar entre 1 e 100 caracteres")
    private String rua;

    @NotNull
    private Short numero;

    @NotNull
    @Size(min = 1, max = 30, message = "BAIRRO deve estar entre 1 e 30 caracteres")
    private String bairro;

    @NotNull
    @Size(min = 1, max = 10, message = "CEP deve estar entre 1 e 10 caracteres")
    private String cep;

    @Size(max = 20, message = "complemento Maximo é de 20 caracteres")
    private String complemento;

    @NotNull
    @Size(min = 1, max = 10, message = "TELEFONE deve estar entre 1 e 10 caracteres")
    private String telefone;

    @NotNull
    @Size(min = 1, max = 11, message = "CELULAR deve estar entre 1 e 11 caracteres")
    private String celular;

    @NotNull
    @Email
    @Size(min = 1, max = 100, message = "EMAIL deve estar entre 1 e 100 caracteres")
    private String email;

}