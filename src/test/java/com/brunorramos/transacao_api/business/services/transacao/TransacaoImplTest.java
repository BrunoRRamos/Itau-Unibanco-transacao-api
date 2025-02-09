package com.brunorramos.transacao_api.business.services.transacao;

import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.OffsetDateTime;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do service implementation de Transação")
class TransacaoImplTest {

    public final String URI_TRANSACAO = "/transacao";

    @Autowired
    public MockMvc driver;

    @Autowired
    private TransacaoImpl transacao;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        this.transacao.delete();
    }

    @Test
    @DisplayName("Criar transação com sucesso")
    void testaCreateTransacaoComSucesso() throws Exception {
        // Arrange
        TransacaoDTO transacaoDTO = new TransacaoDTO(100.0, OffsetDateTime.now());

        // Act
        String responseJsonString = driver.perform(post(URI_TRANSACAO)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isCreated())
                .andDo(print())// Codigo 201
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @DisplayName("Criar transação com valor igual a zero")
    void testaCreateTransacaoComValorIgualZero() throws Exception {
        // Arrange
        TransacaoDTO transacaoDTO = new TransacaoDTO(0.0, OffsetDateTime.now());

        // Act
        String responseJsonString = driver.perform(post(URI_TRANSACAO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isCreated())
                .andDo(print())// Codigo 201
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @DisplayName("Criar transação com valor menor que zero")
    void testaCreateTransacaoComValorMenorQueZero() throws Exception {
        // Arrange
        TransacaoDTO transacaoDTO = new TransacaoDTO(-100.0, OffsetDateTime.now());

        // Act
        String responseJsonString = driver.perform(post(URI_TRANSACAO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print())// Codigo 422
                .andReturn().getResponse().getContentAsString();
    }

   @Test
   @DisplayName("Criar transação com dataHora no futuro")
    void testaCreateTransacaoComDataHoraNoFuturo() throws Exception {
       // Arrange
        TransacaoDTO transacaoDTO = new TransacaoDTO(100.0, OffsetDateTime.now().plusDays(1));

       // Act
       String responseJsonString = driver.perform(post(URI_TRANSACAO)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(transacaoDTO)))
               .andExpect(status().isUnprocessableEntity())
               .andDo(print())// Codigo 422
               .andReturn().getResponse().getContentAsString();
    }

    @Test
    @DisplayName("Criar transação com dataHora no passado")
    void testaCreateTransacaoComDataHoraNoPassado() throws Exception {
        // Arrange
        TransacaoDTO transacaoDTO = new TransacaoDTO(100.0, OffsetDateTime.now().minusDays(1));

        // Act
        String responseJsonString = driver.perform(post(URI_TRANSACAO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isCreated())
                .andDo(print())// Codigo 201
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @DisplayName("Buscar transações nos ultimos 60 segundos")
    void buscarTransacoesNosUltimos60Segundos() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));

        List<TransacaoDTO> transacoes =  this.transacao.buscarTransacoes(60);

        assertEquals(3, transacoes.size());
    }

    @Test
    @DisplayName("Buscar transações sem transações registradas")
    void buscarTransacoesSemTransacoes() throws Exception {
        List<TransacaoDTO> transacoes =  this.transacao.buscarTransacoes(60);

        assertEquals(0, transacoes.size());
    }

    @Test
    void delete() {
        OffsetDateTime now = OffsetDateTime.now();
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));
        this.transacao.createTransacao(new TransacaoDTO(100.0, now));

        List<TransacaoDTO> transacoes =  this.transacao.buscarTransacoes(60);
        assertEquals(3, transacoes.size());

        this.transacao.delete();
        List<TransacaoDTO> transacoesAfterDelete =  this.transacao.buscarTransacoes(60);
        assertEquals(0, transacoesAfterDelete.size());
    }
}