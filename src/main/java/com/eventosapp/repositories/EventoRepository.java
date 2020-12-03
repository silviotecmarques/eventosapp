package com.eventosapp.repositories;

import com.eventosapp.models.EventoModel;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<EventoModel, Long>{
    EventoModel findByCodigo(long codigo);
}
