package com.brunorramos.transacao_api.controller;

import com.brunorramos.transacao_api.business.services.estatisticas.EstatisticasService;
import com.brunorramos.transacao_api.business.services.transacao.TransacaoService;
import com.brunorramos.transacao_api.controller.dtos.EstatisticasResponseDTO;
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
    public ResponseEntity<EstatisticasResponseDTO> gerarEstatisticas(
            @RequestParam(value = "intervalo de busca", required = false, defaultValue = "60") Integer intervaloDeBusca) {
        EstatisticasResponseDTO estatistica = this.estatisticasService.calcularEstatisticas(intervaloDeBusca);

        return ResponseEntity.ok(estatistica);
    }
}
