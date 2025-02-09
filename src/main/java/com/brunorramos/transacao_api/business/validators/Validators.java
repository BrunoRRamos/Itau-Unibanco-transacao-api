package com.brunorramos.transacao_api.business.validators;

import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;
import com.brunorramos.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@Slf4j
public class Validators {

    public static void validaData(OffsetDateTime data) {
        if(data.isAfter(OffsetDateTime.now())) {
            log.error("Data Hora maiores que a data atual");
            throw new UnprocessableEntity("Data Hora maiores que a data atual");
        }
    }

    public static void validaValor(Double value) {
        if(value <= 0) {
            log.error("Valor menor que zero");
            throw new UnprocessableEntity("Valor menor que zero");
        }
    }

    public static void validaNulos(TransacaoDTO transacao) {
        boolean validaValor = transacao.valor() != null;
        boolean validaData = transacao.dataHora() != null;

        if(validaValor && validaData) {
            log.error("Valor ou Data hora nulos");
            throw new UnprocessableEntity("Valor ou Data hora nulos");
        }
    }
}
