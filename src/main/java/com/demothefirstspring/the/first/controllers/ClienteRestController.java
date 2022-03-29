package com.demothefirstspring.the.first.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demothefirstspring.the.first.models.entity.Cliente;
import com.demothefirstspring.the.first.models.entity.Region;
import com.demothefirstspring.the.first.models.services.IClienteService;
import com.demothefirstspring.the.first.models.services.IUploadFileService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	
	private IUploadFileService uploadService;
	
	
	//GET 
	@GetMapping("/clientes")
	public List<Cliente>index(){
		return clienteService.findAll();
		
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente>index(@PathVariable Integer page){
		return clienteService.findAll(PageRequest.of(page, 3));
		
	}
	
	
	//GET BY ID
	@Secured({"ROLE_ADMIN","ROLE_USER"})
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
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	
	public ResponseEntity<?>create(@Valid @RequestBody Cliente cliente, BindingResult result) {	
		
		Cliente clienteNew = null;
		Map<String, Object> response= new HashMap<>();
		
		if(result.hasErrors()) {
			/*List<String> errors= new ArrayList<>();
			for(FieldError err: result.getFieldErrors()){
				errors.add("El campo "+ err.getField()+ " " + err.getDefaultMessage());
			}  ERROR ANTES DE JAVA 8*/
			List<String> errors= result.getFieldErrors()
					.stream()
					.map(err->"El campo '"+ err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
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
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?>update(@Valid @RequestBody Cliente cliente,BindingResult result, @PathVariable Long id) {
		
		Cliente clienteActual= clienteService.findById(id);
		
		Cliente clienteUpdated=null;
		Map<String, Object> response= new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors= result.getFieldErrors().stream().map(err->
			"El campo "+ err.getField()+ " " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(clienteActual==null) {
			response.put("mensaje", "Error no se pudo editar, el cliente con ID: ".concat(id.toString().concat(" ,no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {			
			clienteActual.setSurname(cliente.getSurname());
			clienteActual.setName(cliente.getName());
			clienteActual.setPhoto(cliente.getPhoto());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			
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
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?>delete(@PathVariable Long id) {
		Map<String, Object> response= new HashMap<>();
		
		try {
			 Cliente cliente = clienteService.findById(id);
			 String nombreFotoAnterior=cliente.getPhoto();
			 uploadService.deletePhoto(nombreFotoAnterior);
			clienteService.delete(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido eliminado con éxito!");
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/clientes/upload")
	 public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file, @RequestParam("id") Long id){
		 Map<String, Object> response= new HashMap<>();
		
		 Cliente cliente = clienteService.findById(id);
		 
		 if(!file.isEmpty()) {
			 
			 String fileName= null;
			 try {
			
				fileName=uploadService.copy(file);
				
				
			 }catch(IOException e) {
					response.put("mensaje", "Error al subir la iamgen a la base de datos" );
					response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			 }
			 
			 String nombreFotoAnterior=cliente.getPhoto();
			 
			 uploadService.deletePhoto(nombreFotoAnterior);
			
			 cliente.setPhoto(fileName);
			 
			 clienteService.save(cliente);
			 
			 response.put("cliente", cliente);
			 response.put("mensaje", "Image uploaded succesfully: "+ fileName);
		 }
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 }
	
	
	@GetMapping("/uploads/img/{photoName:.+}")
	public ResponseEntity<Resource>verFoto(@PathVariable String photoName){
		
		Resource recurso=null;
		
		
		try {
			recurso= uploadService.uplodad(photoName);
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
		
			HttpHeaders cabecera = new HttpHeaders();
			cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);

	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listRegions(){
		return clienteService.findAllRegions();
	}
	
}
