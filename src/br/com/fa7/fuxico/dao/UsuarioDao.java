package br.com.fa7.fuxico.dao;

import java.util.List;

import br.com.fa7.fuxico.model.Usuario;

public interface UsuarioDao extends GenericDao {
	public List<Usuario> list();
	public Usuario load( Long id );
	public boolean isLoginExiste(String login);
	public Usuario login( String nome, String senha );
}