package com.devgabriel.agendador_tarefas.business;


import com.devgabriel.agendador_tarefas.business.dto.TarefasDTO;
import com.devgabriel.agendador_tarefas.business.mapper.TarefasConverter;
import com.devgabriel.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.devgabriel.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.devgabriel.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.devgabriel.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto){

        String email = jwtUtil.extrairEmailToken(token.substring(7));


        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAngendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal)) ;
    }


    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

       return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));

    }

}
