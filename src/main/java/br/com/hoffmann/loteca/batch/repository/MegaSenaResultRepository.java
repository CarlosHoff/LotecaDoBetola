package br.com.hoffmann.loteca.batch.repository;

import br.com.hoffmann.loteca.domain.entitys.MegaSenaResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MegaSenaResultRepository extends JpaRepository<MegaSenaResult, Long> {

    MegaSenaResult findByConcurso(Long concurso);

    List<MegaSenaResult> findTopByOrderByCreateDtDesc();

}
