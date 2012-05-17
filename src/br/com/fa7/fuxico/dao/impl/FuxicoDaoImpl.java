package br.com.fa7.fuxico.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.model.Fuxico;

@Component
@RequestScoped
public class FuxicoDaoImpl extends GenericDaoImpl implements FuxicoDao {

	public Fuxico load(Long fuxicoId) {
		return (Fuxico) getSession().load(Fuxico.class, fuxicoId);
	}

	@SuppressWarnings("unchecked")
	public List<Fuxico> listaFuxicosByUsuario(Long usuarioId) {
		Criteria criteria = getSession().createCriteria(Fuxico.class, "f");
		criteria.add(Restrictions.eq("f.usuario.id", usuarioId));
		criteria.addOrder(Order.asc("f.data"));

		return criteria.list();
	}
}