package br.com.hoffmann.loteca.controller;

import br.com.hoffmann.loteca.domain.request.ApostaRequest;
import br.com.hoffmann.loteca.domain.response.ApostaResponse;
import br.com.hoffmann.loteca.service.LotecaService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(path = "/v1/loteca")
public class ApostaController {

    @Autowired
    private LotecaService service;

    @PostMapping(value = "/cadastraAposta", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity cadastraAposta(@RequestBody ApostaRequest request, Long usuarioID) {
        service.cadastraAposta(request, usuarioID);
        return new ResponseEntity<>(CREATED);
    }

    @RequestMapping(value = "/deletaAposta/{id}", method = RequestMethod.DELETE)
    public void deletaAposta(
            @PathVariable(value = "id") @NotNull Long id) {
        service.deletaAposta(id);
    }

    @RequestMapping(value = "/updateAposta/{id}", method = RequestMethod.PUT)
    @Validated(ApostaRequest.class)
    public void updateAposta(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid ApostaRequest request) {
        service.updateAposta(request, id);
    }

    @RequestMapping(value = "/buscaApostas", method = RequestMethod.GET)
    public ResponseEntity<List<ApostaResponse>> buscaApostas() {
        List<ApostaResponse> response = service.buscaApostas();
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/buscaApostasPorJogardor/{jogador}", method = RequestMethod.GET)
    public ResponseEntity<List<ApostaResponse>> buscaApostasPorJogardor(Long jogador) {
        List<ApostaResponse> response = service.buscaApostasPorJogardor(jogador);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/buscaApostasPorID/{apostaID}", method = RequestMethod.GET)
    public ApostaResponse buscaApostasPorID(Long apostaID) throws NotFoundException {
        return service.buscaApostasPorID(apostaID);
    }

}
