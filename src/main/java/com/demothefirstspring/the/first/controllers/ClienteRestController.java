package com.demothefirstspring.the.first.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demothefirstspring.the.first.models.entity.Cliente;
import com.demothefirstspring.the.first.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	//GET 
	@GetMapping("/clientes")
	public List<Cliente>index(){
		return clienteService.findAll();
		
	}
	
	//GET BY ID
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id) {
		return clienteService.findById(id);
		
	}
	
	//CREATE
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) {	
		return clienteService.save(cliente);
	}
	
	//EDITAR
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual= clienteService.findById(id);
		
		clienteActual.setSurname(cliente.getSurname());
		clienteActual.setName(cliente.getName());
		clienteActual.setPhoto(cliente.getPhoto());
		clienteActual.setCreateAt(cliente.getCreateAt());
		
		return clienteService.save(clienteActual);
	}
	
	//BORRAR
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
}
