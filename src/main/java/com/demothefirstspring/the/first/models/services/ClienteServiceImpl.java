package com.demothefirstspring.the.first.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demothefirstspring.the.first.models.dao.IClienteDao;
import com.demothefirstspring.the.first.models.dao.IFacturaDao;
import com.demothefirstspring.the.first.models.dao.IProductoDao;
import com.demothefirstspring.the.first.models.entity.Cliente;
import com.demothefirstspring.the.first.models.entity.Factura;
import com.demothefirstspring.the.first.models.entity.Producto;
import com.demothefirstspring.the.first.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired 
	private IProductoDao productoDao;
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteDao.findAll(pageable);
	}
	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegions() {
		// TODO Auto-generated method stub
		return clienteDao.findAllRegions();
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	public Factura saveFactura(Factura factura) {
		// TODO Auto-generated method stub
		return facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly=true)
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Producto> findProductoByNombre(String term) {
		return productoDao.findByNombreContainingIgnoreCase(term);
	
	}
	

}
