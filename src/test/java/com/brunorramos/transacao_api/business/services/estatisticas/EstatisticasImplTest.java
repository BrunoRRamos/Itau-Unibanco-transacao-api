package com.brunorramos.transacao_api.business.services.estatisticas;

import com.brunorramos.transacao_api.business.services.transacao.TransacaoImpl;
import com.brunorramos.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do service implementation de Estatistica")
class EstatisticasImplTest {

    public final String URI_TRANSACAO = "/estatistica";

    @Autowired
    public MockMvc driver;

    @Autowired
    private TransacaoImpl transacao;

    @Autowired
    private EstatisticasImpl estatistica;

    @AfterEach
    void tearDown() {
        this.transacao.delete();
    }

    @Test
    @DisplayName("Calcular estatisticas")
    void calcularEstatisticas() {
        OffsetDateTime now = OffsetDateTime.now();
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));
        EstatisticasResponseDTO modeloReferencia = new EstatisticasResponseDTO(3L, 300.0, 100.0, 100.0, 100.0);
        EstatisticasResponseDTO estatisticas = this.estatistica.calcularEstatisticas(60);

        assertEquals(estatisticas, modeloReferencia);
    }

    @Test
    @DisplayName("Calcular estatisticas quando não há transações")
    void calcularEstatisticasSemTransacoes() {
        EstatisticasResponseDTO modeloReferencia = new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        EstatisticasResponseDTO estatisticas = this.estatistica.calcularEstatisticas(60);

        assertEquals(estatisticas, modeloReferencia);
    }
}