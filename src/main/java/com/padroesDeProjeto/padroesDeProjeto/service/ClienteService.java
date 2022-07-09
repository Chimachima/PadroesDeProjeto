package com.padroesDeProjeto.padroesDeProjeto.service;

import com.padroesDeProjeto.padroesDeProjeto.model.ClienteModel;

import java.util.Optional;

public interface ClienteService {
    Iterable<ClienteModel> buscarTodos();
    Optional<ClienteModel> buscarPorID(Long id);
    void inserir(ClienteModel clienteModel);
    void atualizar(Long id, ClienteModel clienteModel);
    void deletar(ClienteModel clienteModel);


}
