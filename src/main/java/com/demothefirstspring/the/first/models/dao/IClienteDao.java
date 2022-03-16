package com.demothefirstspring.the.first.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.demothefirstspring.the.first.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
