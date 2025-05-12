package com.infuse.service;

import com.infuse.configuration.exception.RecursoNaoEncontradoException;
import com.infuse.configuration.kafka.KafkaTopicsProperties;
import com.infuse.controller.model.response.CreditoResponse;
import com.infuse.repository.CreditoRepository;
import com.infuse.service.mapper.CreditoMapper;
import com.infuse.service.model.Credito;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTopicsProperties kafkaTopics;
    private final CreditoMapper creditoMapper;

    @Override
    public List<CreditoResponse> buscarPorNumeroNfse(String numeroNfse) {
        String mensagem = "Consulta por NFS-e: " + numeroNfse;
        log.info("Enviando mensagem Kafka → tópico: {}, conteúdo: {}", kafkaTopics.getConsultaNfse(), mensagem);
        kafkaTemplate.send(kafkaTopics.getConsultaNfse(), mensagem);

        List<Credito> creditos = creditoRepository.findByNumeroNfse(numeroNfse);
        log.info("Consulta retornou {} crédito(s) para NFS-e {}", creditos.size(), numeroNfse);

        List<CreditoResponse> responseList = creditoMapper.toResponseList(creditos);
        log.info("CreditoResponseList {}", responseList);

        return responseList;
    }

    @Override
    public CreditoResponse buscarPorNumeroCredito(String numeroCredito) {
        String mensagem = "Consulta por Crédito: " + numeroCredito;
        log.info("Enviando mensagem Kafka → tópico: {}, conteúdo: {}", kafkaTopics.getConsultaNfse(), mensagem);
        kafkaTemplate.send(kafkaTopics.getConsultaNfse(), mensagem);

        Credito credito = creditoRepository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> {
                    log.warn("Crédito não encontrado para número: {}", numeroCredito);
                    return new RecursoNaoEncontradoException("Crédito não encontrado");
                });
        log.info("Consulta retornou o crédito: {}", credito);

        CreditoResponse response = creditoMapper.toResponse(credito);
        log.info("CreditoResponse {}", response);

        return response;
    }
}
