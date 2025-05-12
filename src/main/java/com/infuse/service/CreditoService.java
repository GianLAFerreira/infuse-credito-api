package com.infuse.service;

import com.infuse.controller.model.response.CreditoResponse;
import com.infuse.service.model.Credito;

import java.util.List;

public interface CreditoService {
    List<CreditoResponse> buscarPorNumeroNfse(String numeroNfse);
    CreditoResponse buscarPorNumeroCredito(String numeroCredito);
}
