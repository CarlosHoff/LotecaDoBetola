package br.com.hoffmann.loteca.batch.service;

import br.com.hoffmann.loteca.batch.domain.response.MegaSenaResponse;
import br.com.hoffmann.loteca.batch.repository.MegaSenaResultRepository;
import br.com.hoffmann.loteca.domain.entitys.Aposta;
import br.com.hoffmann.loteca.domain.entitys.MegaSenaResult;
import br.com.hoffmann.loteca.repository.ApostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@EnableScheduling
@Service
@Transactional(value = "transactionManager", noRollbackFor = {})
public class BatchService {
    private static final Logger LOG = LoggerFactory.getLogger(BatchService.class);
    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;

    @Autowired
    private MegaSenaResultRepository megaSenaResultRepository;

    @Autowired
    private ApostaRepository apostaRepository;

    //TODO SCHEDULLADO PARA EXECUTAR TODA QUARTA E SABADO AS 20:05
    @Scheduled(cron = "0 5 20 * 2,5 *")
    @Scheduled(fixedDelay = MINUTO)
    public void getResultsMegaSena() {
        ResponseEntity<MegaSenaResponse> resultMega = getResult();
        MegaSenaResult result = megaSenaResultRepository.findByConcurso(Objects.requireNonNull(resultMega.getBody()).getNumero_concurso());
        if (!Objects.nonNull(result)) {
            megaSenaResultRepository.save(new MegaSenaResult(resultMega));
        } else {
            LOG.info("Concurso {} já existe em nossa base", resultMega.getBody().getNumero_concurso());
        }
    }

    //TODO SCHEDULLADO PARA EXECUTAR TODA QUARTA E SABADO AS 20:10
    @Scheduled(cron = "0 10 20 * 2,5 *")
    public void marcaAcertos() {
        List<MegaSenaResult> lastResult = megaSenaResultRepository.findTopByOrderByCreateDtDesc();
        List<Aposta> apostaList = apostaRepository.findAll();
        for (Aposta aposta : apostaList) {
            for (MegaSenaResult result : lastResult) {
                validaDezenaUm(aposta, result);
                validaDezenaDois(aposta, result);
                validaDezenaTres(aposta, result);
                validaDezenaQuatro(aposta, result);
                validaDezenaCinco(aposta, result);
                validaDezenaSeis(aposta, result);
                validaDezenaSete(aposta, result);
                validaDezenaOito(aposta, result);
                validaDezenaNove(aposta, result);
                validaDezenaDez(aposta, result);
            }
        }
        apostaRepository.saveAll(apostaList);
    }

    //TODO SCHEDULLADO PARA EXECUTAR TODA QUARTA E SABADO AS 20:15
    @Scheduled(cron = "0 15 20 * 2,5 *")
    public void verificaGanhadores() {
        List<Aposta> apostaList = apostaRepository.findAll();
        for (Aposta aposta : apostaList) {
            if (aposta.getDezenaUmFlag().equals(true) &&
                    aposta.getDezenaDoisFlag().equals(true)  &&
                    aposta.getDezenaTresFlag().equals(true)  &&
                    aposta.getDezenaQuatroFlag().equals(true) &&
                    aposta.getDezenaCincoFlag().equals(true)  &&
                    aposta.getDezenaSeisFlag().equals(true)  &&
                    aposta.getDezenaSeteFlag().equals(true)  &&
                    aposta.getDezenaOitoFlag().equals(true)  &&
                    aposta.getDezenaNoveFlag().equals(true)  &&
                    aposta.getDezenaDezFlag().equals(true)) {
                System.out.println("Ganhador:" + aposta.getUsuario().getNome());
            }
        }
    }

    private ResponseEntity<MegaSenaResponse> getResult() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://apiloterias.com.br/app/resultado?loteria=megasena&token=mq8G19j4vjYVIgH&concurso=";
        return restTemplate.getForEntity(url, MegaSenaResponse.class);
    }

    public void validaDezenaDez(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaDez().equals(result.getDezenaUM()) ||
                aposta.getDezenaDez().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaDez().equals(result.getDezenaTRES()) ||
                aposta.getDezenaDez().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaDez().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaDez().equals(result.getDezenaSEIS())) {
            aposta.setDezenaDezFlag(true);

        }
    }

    public void validaDezenaNove(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaNove().equals(result.getDezenaUM()) ||
                aposta.getDezenaNove().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaNove().equals(result.getDezenaTRES()) ||
                aposta.getDezenaNove().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaNove().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaNove().equals(result.getDezenaSEIS())) {
            aposta.setDezenaNoveFlag(true);

        }
    }

    public void validaDezenaOito(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaOito().equals(result.getDezenaUM()) ||
                aposta.getDezenaOito().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaOito().equals(result.getDezenaTRES()) ||
                aposta.getDezenaOito().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaOito().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaOito().equals(result.getDezenaSEIS())) {
            aposta.setDezenaOitoFlag(true);

        }
    }

    public void validaDezenaSete(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaSete().equals(result.getDezenaUM()) ||
                aposta.getDezenaSete().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaSete().equals(result.getDezenaTRES()) ||
                aposta.getDezenaSete().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaSete().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaSete().equals(result.getDezenaSEIS())) {
            aposta.setDezenaSeteFlag(true);

        }
    }

    public void validaDezenaSeis(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaSeis().equals(result.getDezenaUM()) ||
                aposta.getDezenaSeis().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaSeis().equals(result.getDezenaTRES()) ||
                aposta.getDezenaSeis().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaSeis().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaSeis().equals(result.getDezenaSEIS())) {
            aposta.setDezenaSeisFlag(true);

        }
    }

    public void validaDezenaCinco(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaCinco().equals(result.getDezenaUM()) ||
                aposta.getDezenaCinco().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaCinco().equals(result.getDezenaTRES()) ||
                aposta.getDezenaCinco().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaCinco().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaCinco().equals(result.getDezenaSEIS())) {
            aposta.setDezenaCincoFlag(true);

        }
    }

    public void validaDezenaQuatro(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaQuatro().equals(result.getDezenaUM()) ||
                aposta.getDezenaQuatro().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaQuatro().equals(result.getDezenaTRES()) ||
                aposta.getDezenaQuatro().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaQuatro().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaQuatro().equals(result.getDezenaSEIS())) {
            aposta.setDezenaQuatroFlag(true);

        }
    }

    public void validaDezenaTres(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaTres().equals(result.getDezenaUM()) ||
                aposta.getDezenaTres().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaTres().equals(result.getDezenaTRES()) ||
                aposta.getDezenaTres().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaTres().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaTres().equals(result.getDezenaSEIS())) {
            aposta.setDezenaTresFlag(true);

        }
    }

    public void validaDezenaDois(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaDois().equals(result.getDezenaUM()) ||
                aposta.getDezenaDois().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaDois().equals(result.getDezenaTRES()) ||
                aposta.getDezenaDois().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaDois().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaDois().equals(result.getDezenaSEIS())) {
            aposta.setDezenaDoisFlag(true);

        }
    }

    public void validaDezenaUm(Aposta aposta, MegaSenaResult result) {
        if (aposta.getDezenaUm().equals(result.getDezenaUM()) ||
                aposta.getDezenaUm().equals(result.getDezenaDOIS()) ||
                aposta.getDezenaUm().equals(result.getDezenaTRES()) ||
                aposta.getDezenaUm().equals(result.getDezenaQUATRO()) ||
                aposta.getDezenaUm().equals(result.getDezenaCINCO()) ||
                aposta.getDezenaUm().equals(result.getDezenaSEIS())) {
            aposta.setDezenaUmFlag(true);

        }
    }
}

