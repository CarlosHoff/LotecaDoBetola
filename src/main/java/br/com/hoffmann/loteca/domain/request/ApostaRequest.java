package br.com.hoffmann.loteca.domain.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class ApostaRequest implements Serializable {

    private List<Long> numeros;
}
