package br.com.fa7.fuxico.dao.impl;

import java.util.List;

import org.hibernate.Criteria;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.model.Fuxico;

@Component @RequestScoped
public class FuxicoDaoImpl extends GenericDaoImpl implements FuxicoDao {

	@SuppressWarnings("unchecked")
	public List<Fuxico> list() {
		Criteria criteria = criteriosDeBuscaDeFuxico();
		
		return criteria.list();
	}

	private Criteria criteriosDeBuscaDeFuxico() {
		Criteria criteria = getSession().createCriteria(Fuxico.class, "fuxico");
		
		criteria.createCriteria("fuxico.usuario", "usuario");
		criteria.createCriteria("fuxico.data", "data");
		criteria.createCriteria("fuxico.fuxico", "fuxico");

		return criteria;			
	}
}