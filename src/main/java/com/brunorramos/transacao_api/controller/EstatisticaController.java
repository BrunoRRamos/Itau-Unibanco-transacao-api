package com.brunorramos.transacao_api.controller;

import com.brunorramos.transacao_api.business.services.estatisticas.EstatisticasService;
import com.brunorramos.transacao_api.controller.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = "Este endpoint é responsável por criar uma estatisca das ultimas transações em um intervalo de tempo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estatísticas geradas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> gerarEstatisticas(
            @RequestParam(value = "intervalo de busca", required = false, defaultValue = "60") Integer intervaloDeBusca) {
        EstatisticasResponseDTO estatistica = this.estatisticasService.calcularEstatisticas(intervaloDeBusca);

        return ResponseEntity.ok(estatistica);
    }
}
