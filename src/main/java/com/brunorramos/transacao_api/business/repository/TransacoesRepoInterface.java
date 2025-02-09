package com.brunorramos.transacao_api.business.repository;

import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public interface TransacoesRepoInterface {
    public void create(TransacaoDTO transacaoDTO);
    public List<TransacaoDTO> buscarRegistros(OffsetDateTime intervalo);
    public void delete();
}
