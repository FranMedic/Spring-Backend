package com.demothefirstspring.the.first.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.demothefirstspring.the.first.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
