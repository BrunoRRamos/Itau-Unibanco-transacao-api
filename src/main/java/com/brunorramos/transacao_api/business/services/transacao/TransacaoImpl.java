package com.brunorramos.transacao_api.business.services.transacao;

import com.brunorramos.transacao_api.business.repository.TransacaoRepository;
import com.brunorramos.transacao_api.business.validators.Validators;
import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;
import com.brunorramos.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository = new TransacaoRepository();

    @Override
    public void createTransacao(TransacaoDTO transacaoDTO) {
        log.info("Iniciando criação de nova transacao: {}", transacaoDTO);
        System.out.println(transacaoDTO);
        Validators.validaNulos(transacaoDTO);
        Validators.validaValor(transacaoDTO.valor());
        this.transacaoRepository.create(transacaoDTO);
        log.info("Transação criada com sucesso !");
    }

    @Override
    public List<TransacaoDTO> buscarTransacoes(Integer intervalo) {
        log.info("Iniciando a busca de transações referentes aos últimos {} segundos", intervalo);
        OffsetDateTime dataHoraIntervalor = OffsetDateTime.now().minusSeconds(intervalo);
        List<TransacaoDTO> registros = this.transacaoRepository.buscarRegistros(dataHoraIntervalor);
        log.info("Retornando transações com sucesso !");
        return registros;
    }

    @Override
    public void delete() {
        log.info("Iniciando deleção das transações ...");
        this.transacaoRepository.delete();
        log.info("Todas as transações foram apagadas com sucesso !");
    }
}
