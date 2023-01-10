package br.com.hoffmann.loteca.domain.response;

import br.com.hoffmann.loteca.domain.entitys.Aposta;
import br.com.hoffmann.loteca.domain.entitys.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ApostaResponse implements Serializable {

    private Long apostaID;

    private Long dezenaUm;

    private Long dezenaDois;

    private Long dezenaTres;

    private Long dezenaQuatro;

    private Long dezenaCinco;

    private Long dezenaSeis;

    private Long dezenaSete;

    private Long dezenaOito;

    private Long dezenaNove;

    private Long dezenaDez;

    private Usuario usuario;

    private Long qtdAcertos;

    public ApostaResponse(Aposta aposta) {
        this.apostaID = aposta.getApostaID();
        this.dezenaUm = aposta.getDezenaUm();
        this.dezenaDois = aposta.getDezenaDois();
        this.dezenaTres = aposta.getDezenaTres();
        this.dezenaQuatro = aposta.getDezenaQuatro();
        this.dezenaCinco = aposta.getDezenaCinco();
        this.dezenaSeis = aposta.getDezenaSeis();
        this.dezenaSete = aposta.getDezenaSete();
        this.dezenaOito = aposta.getDezenaOito();
        this.dezenaNove = aposta.getDezenaNove();
        this.dezenaDez = aposta.getDezenaDez();
        //this.usuario = aposta.getUsuario();
    }
}
