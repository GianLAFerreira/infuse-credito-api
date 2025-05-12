//package com.infuse.service;
//
//import com.infuse.configuration.exception.RecursoNaoEncontradoException;
//import com.infuse.configuration.kafka.KafkaTopicsProperties;
//import com.infuse.repository.CreditoRepository;
//import com.infuse.service.model.Credito;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CreditoServiceImplTest {
//
//    @Mock
//    private CreditoRepository creditoRepository;
//
//    @Mock
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @Mock
//    private KafkaTopicsProperties kafkaTopics;
//
//    @InjectMocks
//    private CreditoServiceImpl creditoService;
//
//    @Test
//    void deveRetornarListaVaziaQuandoNaoEncontrarCreditoPorNfse() {
//        String nfse = "NFSE001";
//        when(kafkaTopics.getConsultaNfse()).thenReturn("consultas-credito");
//        when(creditoRepository.findByNumeroNfse(nfse)).thenReturn(Collections.emptyList());
//
//        var resultado = creditoService.buscarPorNumeroNfse(nfse);
//
//        assertNotNull(resultado);
//        assertTrue(resultado.isEmpty());
//        verify(kafkaTemplate).send("consultas-credito", "Consulta por NFS-e: " + nfse);
//    }
//
//    @Test
//    void deveRetornarCreditoQuandoNumeroCreditoExiste() {
//        String numeroCredito = "CRED001";
//        Credito credito = Credito.builder()
//                .numeroCredito(numeroCredito)
//                .numeroNfse("NFSE001")
//                .dataConstituicao(LocalDate.now())
//                .valorIssqn(BigDecimal.valueOf(1000))
//                .tipoCredito("ISSQN")
//                .simplesNacional(true)
//                .aliquota(BigDecimal.valueOf(5))
//                .valorFaturado(BigDecimal.valueOf(20000))
//                .valorDeducao(BigDecimal.valueOf(1000))
//                .baseCalculo(BigDecimal.valueOf(19000))
//                .build();
//
//        when(kafkaTopics.getConsultaNfse()).thenReturn("consultas-credito");
//        when(creditoRepository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(credito));
//
//        Credito resultado = creditoService.buscarPorNumeroCredito(numeroCredito);
//
//        assertNotNull(resultado);
//        assertEquals(numeroCredito, resultado.getNumeroCredito());
//        verify(kafkaTemplate).send("consultas-credito", "Consulta por Crédito: " + numeroCredito);
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoNumeroCreditoNaoExiste() {
//        String numeroCredito = "INEXISTENTE";
//        when(kafkaTopics.getConsultaNfse()).thenReturn("consultas-credito");
//        when(creditoRepository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.empty());
//
//        RecursoNaoEncontradoException ex = assertThrows(
//                RecursoNaoEncontradoException.class,
//                () -> creditoService.buscarPorNumeroCredito(numeroCredito)
//        );
//
//        assertEquals("Crédito não encontrado", ex.getMessage());
//        verify(kafkaTemplate).send("consultas-credito", "Consulta por Crédito: " + numeroCredito);
//    }
//}
