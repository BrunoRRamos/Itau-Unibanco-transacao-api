package com.brunorramos.transacao_api.business.services.estatisticas;

import com.brunorramos.transacao_api.business.services.transacao.TransacaoService;
import com.brunorramos.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasImpl implements EstatisticasService {

    private final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticas(Integer intervalo) {
        log.info("Calculando estatisticas para intervalo de {} segundos", intervalo);

        List<TransacaoDTO> transacoes = transacaoService.buscarTransacoes(intervalo);
        DoubleSummaryStatistics estatisticasDasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoDTO::valor).summaryStatistics();

        log.info("Retornando estatisticas com sucesso !");
        return new EstatisticasResponseDTO(
                estatisticasDasTransacoes.getCount(),
                estatisticasDasTransacoes.getSum(),
                estatisticasDasTransacoes.getAverage(),
                estatisticasDasTransacoes.getMin(),
                estatisticasDasTransacoes.getMax()
        );
    }
}
