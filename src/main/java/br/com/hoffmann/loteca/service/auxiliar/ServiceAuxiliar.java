package br.com.hoffmann.loteca.service.auxiliar;

import br.com.hoffmann.loteca.domain.entitys.Aposta;
import br.com.hoffmann.loteca.domain.entitys.Usuario;
import br.com.hoffmann.loteca.domain.request.ApostaRequest;
import br.com.hoffmann.loteca.domain.request.UsuarioRequest;
import br.com.hoffmann.loteca.domain.response.UsuarioResponse;
import br.com.hoffmann.loteca.repository.ApostaRepository;
import br.com.hoffmann.loteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

import static java.lang.String.format;

@Component
@Transactional(value = "transactionManager")
public class ServiceAuxiliar {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ApostaRepository apostaRepository;

    public void alteraAposta(Aposta aposta, ApostaRequest request) {
        aposta.setDezenaUm(request.getNumeros().get(0));
        aposta.setDezenaDois(request.getNumeros().get(1));
        aposta.setDezenaTres(request.getNumeros().get(2));
        aposta.setDezenaQuatro(request.getNumeros().get(3));
        aposta.setDezenaCinco(request.getNumeros().get(4));
        aposta.setDezenaSeis(request.getNumeros().get(5));
        aposta.setDezenaSete(request.getNumeros().get(6));
        aposta.setDezenaOito(request.getNumeros().get(7));
        aposta.setDezenaNove(request.getNumeros().get(8));
        aposta.setDezenaDez(request.getNumeros().get(9));
        apostaRepository.save(aposta);
    }

    public void criaUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setCpf(request.getCpf());
        usuario.setTipoDeUsuario(request.getTipoDeUsuario());
        usuario.setCelular(request.getCelular());
        usuario.setEmail(request.getEmail());

        usuarioRepository.save(usuario);
    }

    public UsuarioResponse atualizaUsuario(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getUsuarioID());
        response.setNome(usuario.getNome());
        response.setCpf(usuario.getCpf());
        response.setTipoDeUsuario(usuario.getTipoDeUsuario());
        response.setCelular(usuario.getCelular());
        response.setEmail(usuario.getEmail());

        return response;
    }

    public void updateExceptions(UsuarioRequest request, Long id) {

        Optional<Usuario> verificaCpf = verificaCpf(request);
        Optional<Usuario> verificaEmail = verificaEmail(request);
        Optional<Usuario> verificaTelefone = verificaTelefone(request);
        Optional<Usuario> verificaCelular = verificaCelular(request);

        if (verificaCpf.isPresent() && !verificaCpf.get().getUsuarioID().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o CPF [%s] cadastrado em nossa base.", request.getCpf()));
        } else if (verificaTelefone.isPresent() && !verificaTelefone.get().getUsuarioID().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o TELEFONE [%s] cadastrado em nossa base.", request.getTelefone()));
        } else if (verificaCelular.isPresent() && !verificaCelular.get().getUsuarioID().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o CELULAR [%s] cadastrado em nossa base.", request.getCelular()));
        } else if (verificaEmail.isPresent() && !verificaEmail.get().getUsuarioID().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o EMAIL [%s] cadastrado em nossa base.", request.getEmail()));
        }
    }

    public void validaSePessoaExiste(UsuarioRequest request) {

        Optional<Usuario> verificaCpf = verificaCpf(request);
        Optional<Usuario> verificaEmail = verificaEmail(request);
        Optional<Usuario> verificaTelefone = verificaTelefone(request);
        Optional<Usuario> verificaCelular = verificaCelular(request);

        if (verificaCpf.isPresent()) {
            throw new NonUniqueResultException(format("CPF [%s] já cadastrado.", request.getCpf()));
        } else if (verificaEmail.isPresent()) {
            throw new NonUniqueResultException(format("EMAIL [%s] já cadastrado.", request.getEmail()));
        } else if (verificaTelefone.isPresent()) {
            throw new NonUniqueResultException(format("TELEFONE [%s] já cadastrado.", request.getTelefone()));
        } else if (verificaCelular.isPresent()) {
            throw new NonUniqueResultException(format("CELULAR [%s] já cadastrado.", request.getCelular()));
        }
    }

    private Optional<Usuario> verificaCpf(UsuarioRequest request) {
        return usuarioRepository.findPessoaByCpf(request.getCpf());
    }

    private Optional<Usuario> verificaEmail(UsuarioRequest request) {
        return usuarioRepository.findPessoaByEmail(request.getEmail());
    }

    private Optional<Usuario> verificaTelefone(UsuarioRequest request) {
        return usuarioRepository.findPessoaByTelefone(request.getTelefone());
    }

    private Optional<Usuario> verificaCelular(UsuarioRequest request) {
        return usuarioRepository.findPessoaByCelular(request.getCelular());
    }
}