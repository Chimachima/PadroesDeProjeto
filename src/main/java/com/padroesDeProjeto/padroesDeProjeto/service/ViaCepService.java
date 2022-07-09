package com.padroesDeProjeto.padroesDeProjeto.service;

import com.padroesDeProjeto.padroesDeProjeto.model.EnderecoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="viaCep", url="viacep.com.br/ws/")
public interface ViaCepService {
    @GetMapping("/{cep}/json/")
    EnderecoModel consultaCEP(@PathVariable("cep") String cep);
}
