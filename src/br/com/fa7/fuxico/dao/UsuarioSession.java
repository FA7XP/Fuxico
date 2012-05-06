package br.com.fa7.fuxico.dao;

import br.com.fa7.fuxico.model.Usuario;



public interface UsuarioSession {

	public Usuario getUsuario();

	public void setUsuario(Usuario usuario);
}