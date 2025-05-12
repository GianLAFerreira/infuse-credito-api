package com.infuse.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CreditoResponseList {
    private List<CreditoResponse> creditoResponseList;
}
