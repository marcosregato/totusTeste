package com.br.totusTeste.controller;


import com.br.totusTeste.model.Conta;
import com.br.totusTeste.repository.ContaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @RequestMapping(value = "/listaConta", method = RequestMethod.GET)
    public List<Conta> get() {
        return contaRepository.findAll();
    }

    @RequestMapping(value = "/conta/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> getById(@PathVariable(value = "id") long id)
    {
        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isPresent())
            return new ResponseEntity<Conta>(conta.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/conta", method =  RequestMethod.POST)
    public Conta post(@Valid @RequestBody Conta conta)
    {
        return contaRepository.save(conta);
    }

    @RequestMapping(value = "/contaUpdate/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Conta> put(@PathVariable(value = "id") long id, @Valid @RequestBody Conta newConta)
    {
        Optional<Conta> oldConta = contaRepository.findById(id);
        if(oldConta.isPresent()){
            Conta conta = oldConta.get();
            conta.setData_vencimento(newConta.getData_vencimento());
            conta.setData_pagamento(newConta.getData_pagamento());
            conta.setValor(newConta.getValor());
            conta.setDescricao(newConta.getDescricao());
            conta.setSituacao(newConta.getSituacao());
            contaRepository.save(conta);
            return new ResponseEntity<Conta>(conta, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/deleteConta/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id)
    {
        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isPresent()){
            contaRepository.delete(conta.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
