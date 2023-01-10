package br.com.hoffmann.loteca.batch.domain.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class MegaSenaResponse {

    private String nome;
    private Long numero_concurso;
    private String local_realizacao;
    private Boolean rateio_processamento;
    private Boolean acumulou;
    private String valor_acumulado;
    private List<Long> dezenas;

}
