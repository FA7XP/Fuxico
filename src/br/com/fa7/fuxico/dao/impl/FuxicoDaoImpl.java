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
		Criteria criteria = getSession().createCriteria(Fuxico.class, "f");
		criteria.createCriteria("f.usuario", "u");
		
		criteria.createCriteria("f.usuario", "usuario");
		criteria.createCriteria("f.data", "data");
		criteria.createCriteria("f.fuxico", "fuxico");
		
		return criteria.list();
	}
}