package br.com.fa7.fuxico.dao;

import java.util.List;

import br.com.fa7.fuxico.model.Usuario;

public interface UsuarioDao extends GenericDao {
	public List<Usuario> list();
	public List<Usuario> search( Long filtro, Long unidade, String search, Long empresaId );
	public List<Usuario> aniversariantes( Long empresaId );
	public Usuario load( Long id );
	public Usuario load( String login );
	public Usuario login( String nome, String senha );
	//public Boolean verificarUsuarioExiste(String nome);
	public Usuario consultarUsuarioPorLogin(String login);

}