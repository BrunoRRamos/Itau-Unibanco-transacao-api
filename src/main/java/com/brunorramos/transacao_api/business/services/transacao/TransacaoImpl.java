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
    public void createTransacao(TransacaoDTO transacaoDTO) throws UnprocessableEntity {
        log.info("Iniciando criação de nova transacao");
        Validators.validaNulos(transacaoDTO);
        Validators.validaValor(transacaoDTO.valor());
        this.transacaoRepository.create(transacaoDTO);
    }

    @Override
    public List<TransacaoDTO> buscarTransacoes(Integer intervalo) {
        OffsetDateTime dataHoraIntervalor = OffsetDateTime.now().minusSeconds(intervalo);
        List<TransacaoDTO> registros = this.transacaoRepository.buscarRegistros(dataHoraIntervalor);
        return registros;
    }

    @Override
    public void delete() {
        this.transacaoRepository.delete();
    }
}
