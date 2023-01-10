package br.com.hoffmann.loteca.service;

import br.com.hoffmann.loteca.domain.entitys.Aposta;
import br.com.hoffmann.loteca.domain.request.ApostaRequest;
import br.com.hoffmann.loteca.domain.response.ApostaResponse;
import br.com.hoffmann.loteca.repository.ApostaRepository;
import br.com.hoffmann.loteca.repository.UsuarioRepository;
import br.com.hoffmann.loteca.service.auxiliar.ServiceAuxiliar;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(value = "transactionManager")
public class LotecaService {

    @Autowired
    private ApostaRepository apostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServiceAuxiliar auxiliar;

    public void cadastraAposta(ApostaRequest request, Long usuarioID) {
        Aposta aposta = new Aposta(request);
        aposta.setUsuario(usuarioRepository.getById(usuarioID));
        apostaRepository.save(aposta);
    }

    public void deletaAposta(Long id) {
        apostaRepository.deleteById(id);
    }

    public void updateAposta(@Valid ApostaRequest request, Long id) {
        Optional<Aposta> apostaOptional = apostaRepository.findById(id);
        apostaOptional.ifPresent(aposta -> auxiliar.alteraAposta(aposta, request));
    }

    public List<ApostaResponse> buscaApostas() {
        List<Aposta> apostas = apostaRepository.findAll();
        return apostas.stream().map(ApostaResponse::new).collect(Collectors.toList());
    }

    public List<ApostaResponse> buscaApostasPorJogardor(Long jogardorId) {
        List<Aposta> apostas = apostaRepository.findAllById(Collections.singleton(jogardorId));
        return apostas.stream().map(ApostaResponse::new).collect(Collectors.toList());
    }

    public ApostaResponse buscaApostasPorID(Long apostaID) throws NotFoundException {
        Optional<Aposta> aposta = apostaRepository.findById(apostaID);
        if (!aposta.isPresent()) {
            throw new NotFoundException("Aposta não encontrada");
        }
        return new ApostaResponse(aposta.get());
    }
}
