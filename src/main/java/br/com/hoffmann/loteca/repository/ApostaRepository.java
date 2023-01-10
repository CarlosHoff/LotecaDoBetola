package br.com.hoffmann.loteca.repository;

import br.com.hoffmann.loteca.domain.entitys.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {
}
