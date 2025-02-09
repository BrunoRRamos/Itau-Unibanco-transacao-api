package com.brunorramos.transacao_api.controller;

import com.brunorramos.transacao_api.business.services.transacao.TransacaoService;
import com.brunorramos.transacao_api.controller.dtos.TransacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoDTO transacaoDTO) {
        this.transacaoService.createTransacao(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limparTransacoes() {
        this.transacaoService.delete();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
