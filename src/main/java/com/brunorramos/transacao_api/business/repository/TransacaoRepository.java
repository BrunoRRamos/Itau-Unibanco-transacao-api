package com.brunorramos.transacao_api.business.repository;

import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoRepository implements TransacoesRepoInterface{

    private final ArrayList<TransacaoDTO> transacoesRepository = new ArrayList<>();

    @Override
    public void create(TransacaoDTO transacaoDTO) {
        this.transacoesRepository.add(transacaoDTO);
    }

    @Override
    public List<TransacaoDTO> buscarRegistros(OffsetDateTime intervalo) {
        return this.transacoesRepository.stream()
                .filter(transacao -> transacao.dataHora().isAfter(intervalo))
                .toList();
    }

    @Override
    public void delete() {
        this.transacoesRepository.clear();
    }
}
