package com.brunorramos.transacao_api.business.services.estatisticas;

import com.brunorramos.transacao_api.controller.dtos.EstatisticasResponseDTO;

public interface EstatisticasService {
    public EstatisticasResponseDTO calcularEstatisticas(Integer intervalo);
}
