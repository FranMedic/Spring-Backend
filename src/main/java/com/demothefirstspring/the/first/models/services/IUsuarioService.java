package com.demothefirstspring.the.first.models.services;

import com.demothefirstspring.the.first.models.entity.Usuario;

public interface IUsuarioService  {

	public Usuario findByUsername(String username);
}

