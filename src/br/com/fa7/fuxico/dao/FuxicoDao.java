package br.com.fa7.fuxico.dao;

import java.util.List;

import br.com.fa7.fuxico.model.Fuxico;

public interface FuxicoDao extends GenericDao {
	public Fuxico load(Long fuxicoId);
	public List<Fuxico> listaFuxicosByUsuario(Long usuarioId);
}