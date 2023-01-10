package br.com.hoffmann.loteca.controller;

import br.com.hoffmann.loteca.domain.request.UsuarioRequest;
import br.com.hoffmann.loteca.domain.response.UsuarioResponse;
import br.com.hoffmann.loteca.service.UsuarioService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/cadastraUsuario", method = RequestMethod.POST)
    public ResponseEntity<UsuarioResponse> cadastraUsuario(
            @RequestBody UsuarioRequest request) {
        usuarioService.createUsuario(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deletaUsuario/{id}", method = RequestMethod.DELETE)
    public void deletaUsuario(
            @PathVariable(value = "id") @NotNull Long id) throws NotFoundException {
        usuarioService.deletaUsuario(id);
    }

    @RequestMapping(value = "/updateUsuario/{id}", method = RequestMethod.PUT)
    public void updateUsuario(
            @PathVariable(value = "id") @NotNull Long id, String telefone, String celular,
            @RequestBody @Valid UsuarioRequest request) throws NotFoundException {
        usuarioService.updateUsuario(request, id);
    }

    @RequestMapping(value = "/buscaUsuarios", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioResponse>> buscaUsuarios() throws NotFoundException {
        List<UsuarioResponse> response = usuarioService.buscaUsuarios();
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/buscaUsuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioResponse> buscaUsuario(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok().body(usuarioService.buscaUsuario(id));
    }
}