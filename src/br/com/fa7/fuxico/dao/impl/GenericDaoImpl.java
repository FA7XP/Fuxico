package br.com.fa7.fuxico.dao.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.fa7.fuxico.dao.GenericDao;

public class GenericDaoImpl implements GenericDao {
	
	@Autowired
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void save(Object object) {
		getSession().save(object);
	}
		
	public void update(Object object) {
		getSession().merge(object);
	}

	public void delete(Object object) {
		getSession().delete(object);
	}
}