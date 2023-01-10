package br.com.hoffmann.loteca.domain.entitys;

import br.com.hoffmann.loteca.batch.domain.response.MegaSenaResponse;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name = "MEGA_SENA_RESULTADOS")
public class MegaSenaResult {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEGA_SENA_RESULTADOS")
    @SequenceGenerator(sequenceName = "SQ_MEGA_SENA_RESULTADOS", allocationSize = 1, name = "SQ_MEGA_SENA_RESULTADOS")
    @Column(name = "ID")
    private Long megaResultID;

    @Column(name = "CONCURSO", unique = true)
    private Long concurso;

    @Column(name = "DEZENA_UM")
    private Long dezenaUM;

    @Column(name = "DEZENA_DOIS")
    private Long dezenaDOIS;

    @Column(name = "DEZENA_TRES")
    private Long dezenaTRES;

    @Column(name = "DEZENA_QUATRO")
    private Long dezenaQUATRO;

    @Column(name = "DEZENA_CINCO")
    private Long dezenaCINCO;

    @Column(name = "DEZENA_SEIS")
    private Long dezenaSEIS;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATE_DT")
    private LocalDateTime createDt;

    public MegaSenaResult(ResponseEntity<MegaSenaResponse> response) {
        this.concurso = Objects.requireNonNull(response.getBody()).getNumero_concurso();
        this.dezenaUM = response.getBody().getDezenas().get(0);
        this.dezenaDOIS = response.getBody().getDezenas().get(1);
        this.dezenaTRES = response.getBody().getDezenas().get(2);
        this.dezenaQUATRO = response.getBody().getDezenas().get(3);
        this.dezenaCINCO = response.getBody().getDezenas().get(4);
        this.dezenaSEIS = response.getBody().getDezenas().get(5);
        this.status = "Y";
        this.createDt = LocalDateTime.now();
    }

}
