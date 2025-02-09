package com.brunorramos.transacao_api.business.services.transacao;

import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;

import java.util.List;

public interface TransacaoService {
    public void createTransacao(TransacaoDTO transacaoDTO);
    public List<TransacaoDTO> gerarEstatisticas(Integer intervalo);
    public void delete();
}
