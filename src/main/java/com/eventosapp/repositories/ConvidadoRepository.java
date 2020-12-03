package com.eventosapp.repositories;

import com.eventosapp.models.EventoModel;
import org.springframework.data.repository.CrudRepository;

import com.eventosapp.models.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
    Iterable<Convidado> findByEvento(EventoModel evento);
}
