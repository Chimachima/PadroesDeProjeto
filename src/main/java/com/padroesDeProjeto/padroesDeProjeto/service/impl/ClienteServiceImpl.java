package com.padroesDeProjeto.padroesDeProjeto.service.impl;

import com.padroesDeProjeto.padroesDeProjeto.model.ClienteModel;
import com.padroesDeProjeto.padroesDeProjeto.model.EnderecoModel;
import com.padroesDeProjeto.padroesDeProjeto.repository.ClienteRepository;
import com.padroesDeProjeto.padroesDeProjeto.repository.EnderecoRepository;
import com.padroesDeProjeto.padroesDeProjeto.service.ClienteService;
import com.padroesDeProjeto.padroesDeProjeto.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ViaCepService viaCepService;

    public Iterable<ClienteModel> buscarTodos(){
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> buscarPorID(Long id){
        return clienteRepository.findById(id);
    }

    public void inserir(ClienteModel clienteModel){
        salvarComVerificacaoDeEndereco(clienteModel);
    }

    public void atualizar(Long id, ClienteModel clienteModel){
        if(clienteRepository.findById(id).isPresent()){
            salvarComVerificacaoDeEndereco(clienteModel);
        }
    }

    public void deletar(ClienteModel clienteModel){
        clienteRepository.delete(clienteModel);
    }

    private void salvarComVerificacaoDeEndereco(ClienteModel clienteModel) {
        String cep = clienteModel.getEndereco().getCep();
        EnderecoModel endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            EnderecoModel enderecoNovo = viaCepService.consultaCEP(cep);
            enderecoRepository.save(enderecoNovo);
            return enderecoNovo;
        });
        clienteModel.setEndereco(endereco);
        clienteRepository.save(clienteModel);
    }
}

