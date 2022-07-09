package com.padroesDeProjeto.padroesDeProjeto.repository;

import com.padroesDeProjeto.padroesDeProjeto.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel,Long> {
}
