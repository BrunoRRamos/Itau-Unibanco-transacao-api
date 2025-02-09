package com.brunorramos.transacao_api.controller.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoDTO(Double valor, OffsetDateTime dataHora) {

}
