package com.devgabriel.agendador_tarefas.business.mapper;

import com.devgabriel.agendador_tarefas.business.dto.TarefasDTO;
import com.devgabriel.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);
    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
