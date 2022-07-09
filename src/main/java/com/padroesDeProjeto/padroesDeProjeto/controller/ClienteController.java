package com.padroesDeProjeto.padroesDeProjeto.controller;

import com.padroesDeProjeto.padroesDeProjeto.model.ClienteModel;
import com.padroesDeProjeto.padroesDeProjeto.repository.ClienteRepository;
import com.padroesDeProjeto.padroesDeProjeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    ResponseEntity<Iterable<ClienteModel>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> buscarPorID(@PathVariable("id") Long id){
        Optional<ClienteModel> clienteServiceOptional = clienteService.buscarPorID(id);

        if(clienteServiceOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(clienteServiceOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Encontrado");
    }

    @PostMapping
    ResponseEntity<ClienteModel> inserirContato(@RequestBody ClienteModel clienteModel){
        clienteService.inserir(clienteModel);
        return ResponseEntity.ok(clienteModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> atualizarContato(@PathVariable("id") Long id, @RequestBody ClienteModel clienteModel){
        Optional<ClienteModel> clienteModelOptional = clienteService.buscarPorID(id);
        if (clienteModelOptional.isPresent()){
            clienteService.atualizar(id, clienteModel);
            return ResponseEntity.ok(clienteModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Encontrado");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deletar(@PathVariable("id") Long id){
        Optional<ClienteModel> clienteServiceOptional = clienteService.buscarPorID(id);

        if(clienteServiceOptional.isPresent()){
            clienteService.deletar(clienteServiceOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Encontrado");
    }

}
