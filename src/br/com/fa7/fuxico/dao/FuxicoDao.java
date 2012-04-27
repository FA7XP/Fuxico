package br.com.fa7.fuxico.dao;

import java.util.List;

import br.com.fa7.fuxico.model.Fuxico;

public interface FuxicoDao extends GenericDao {
	public List<Fuxico> list();
	public List<Fuxico> search( Long filtro, Long unidade, String search, Long empresaId );
	public Fuxico load( Long id );
	public Fuxico load( String login );
	public Fuxico login( String nome, String senha );

}