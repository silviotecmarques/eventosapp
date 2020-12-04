package com.eventosapp.repositories;

import com.eventosapp.models.Evento;
import org.springframework.data.repository.CrudRepository;

import com.eventosapp.models.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
    Iterable<Convidado> findByEvento(Evento evento);
    Convidado findByRg(String rg);
}
