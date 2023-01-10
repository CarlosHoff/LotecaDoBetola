package br.com.hoffmann.loteca.domain.entitys;

import br.com.hoffmann.loteca.domain.request.ApostaRequest;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name = "APOSTA")
public class Aposta {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long apostaID;

    @Column(name = "DEZENA_UM")
    private Long dezenaUm;

    @Column(name = "DEZENA_UM_FLAG")
    private Boolean dezenaUmFlag;

    @Column(name = "DEZENA_DOIS")
    private Long dezenaDois;

    @Column(name = "DEZENA_DOIS_FLAG")
    private Boolean dezenaDoisFlag;

    @Column(name = "DEZENA_TRES")
    private Long dezenaTres;

    @Column(name = "DEZENA_TRES_FLAG")
    private Boolean dezenaTresFlag;

    @Column(name = "DEZENA_QUATRO")
    private Long dezenaQuatro;

    @Column(name = "DEZENA_QUATRO_FLAG")
    private Boolean dezenaQuatroFlag;

    @Column(name = "DEZENA_CINCO")
    private Long dezenaCinco;

    @Column(name = "DEZENA_CINCO_FLAG")
    private Boolean dezenaCincoFlag;

    @Column(name = "DEZENA_SEIS")
    private Long dezenaSeis;

    @Column(name = "DEZENA_SEIS_FLAG")
    private Boolean dezenaSeisFlag;

    @Column(name = "DEZENA_SETE")
    private Long dezenaSete;

    @Column(name = "DEZENA_SETE_FLAG")
    private Boolean dezenaSeteFlag;

    @Column(name = "DEZENA_OITO")
    private Long dezenaOito;

    @Column(name = "DEZENA_OITO_FLAG")
    private Boolean dezenaOitoFlag;

    @Column(name = "DEZENA_NOVE")
    private Long dezenaNove;

    @Column(name = "DEZENA_NOVE_FLAG")
    private Boolean dezenaNoveFlag;

    @Column(name = "DEZENA_DEZ")
    private Long dezenaDez;

    @Column(name = "DEZENA_DEZ_FLAG")
    private Boolean dezenaDezFlag;

    @ManyToOne
    @JoinColumn(name = "USUARIO")
    private Usuario usuario;

    public Aposta() {
    }

    public Aposta(ApostaRequest request) {
        this.dezenaUm = request.getNumeros().get(0);
        this.dezenaDois = request.getNumeros().get(1);
        this.dezenaTres = request.getNumeros().get(2);
        this.dezenaQuatro = request.getNumeros().get(3);
        this.dezenaCinco = request.getNumeros().get(4);
        this.dezenaSeis = request.getNumeros().get(5);
        this.dezenaSete = request.getNumeros().get(6);
        this.dezenaOito = request.getNumeros().get(7);
        this.dezenaNove = request.getNumeros().get(8);
        this.dezenaDez = request.getNumeros().get(9);
    }
}
