package com.demothefirstspring.the.first.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demothefirstspring.the.first.models.entity.Cliente;
import com.demothefirstspring.the.first.models.entity.Region;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
 
	@Query("from Region") //la clase Region
	public List<Region>findAllRegions();
	
}
