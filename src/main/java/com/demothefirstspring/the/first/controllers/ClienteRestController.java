package com.demothefirstspring.the.first.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cliente cliente = null;
		Map<String,Object> response= new HashMap<>();
		try {
			cliente= clienteService.findById(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta al servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(cliente== null) {
			response.put("mensaje", "El ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		
		
	}
	
	//CREATE
	@PostMapping("/clientes")
	
	public ResponseEntity<?>create(@RequestBody Cliente cliente) {	
		
		Cliente clienteNew = null;
		Map<String, Object> response= new HashMap<>();
		try {
			clienteNew= clienteService.save(cliente);
		}catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta al servidor");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	//EDITAR
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?>update(@RequestBody Cliente cliente, @PathVariable Long id) {
		
		Cliente clienteActual= clienteService.findById(id);
		
		Cliente clienteUpdated=null;
		Map<String, Object> response= new HashMap<>();
		
		if(clienteActual==null) {
			response.put("mensaje", "Error no se pudo editar, el cliente con ID: ".concat(id.toString().concat(" ,no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {			
			clienteActual.setSurname(cliente.getSurname());
			clienteActual.setName(cliente.getName());
			clienteActual.setPhoto(cliente.getPhoto());
			clienteActual.setCreateAt(cliente.getCreateAt());
			
			clienteUpdated= clienteService.save(clienteActual);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	//BORRAR
	@DeleteMapping("/clientes/{id}")
	
	public ResponseEntity<?>delete(@PathVariable Long id) {
		Map<String, Object> response= new HashMap<>();
		
		try {
			clienteService.delete(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido eliminado con éxito!");
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
}
