package com.infuse.service.mapper;

import com.infuse.controller.model.response.CreditoResponse;
import com.infuse.controller.model.response.CreditoResponseList;
import com.infuse.service.model.Credito;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditoMapper {
    CreditoResponse toResponse(Credito credito);
    List<CreditoResponse> toResponseList(List<Credito> creditos);
}
