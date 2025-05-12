package com.infuse.controller;

import com.infuse.controller.model.response.CreditoResponse;
import com.infuse.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@RequiredArgsConstructor
public class CreditoController {

    private final CreditoService creditoService;

    @GetMapping("/{numeroNfse}")
    public List<CreditoResponse> getCreditosPorNfse(@PathVariable String numeroNfse) {
        return creditoService.buscarPorNumeroNfse(numeroNfse);
    }

    @GetMapping("/credito/{numeroCredito}")
    public CreditoResponse getCreditoPorNumero(@PathVariable String numeroCredito) {
        return creditoService.buscarPorNumeroCredito(numeroCredito);
    }
}
