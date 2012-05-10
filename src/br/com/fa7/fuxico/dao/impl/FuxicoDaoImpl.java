package br.com.fa7.fuxico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.model.Fuxico;

@Component
@RequestScoped
public class FuxicoDaoImpl extends GenericDaoImpl implements FuxicoDao {

	private EntityManagerFactory fabrica;

	public FuxicoDaoImpl() {
		fabrica = Persistence.createEntityManagerFactory("fuxico");
	}

	@SuppressWarnings("unchecked")
	public List<Fuxico> list() {
		Criteria criteria = getSession().createCriteria(Fuxico.class, "f");
		// criteria.createCriteria("f.usuario", "u");
		//		
		// criteria.createCriteria("f.usuario", "usuario");
		// criteria.createCriteria("f.data", "data");
		//criteria.createCriteria("f.fuxico", "fuxico").add(
		//		Restrictions.eq("usuario_id", id));

		return criteria.list();
	}

	public void salvarFuxico(Fuxico fuxico) {
		EntityManager gerente = fabrica.createEntityManager();
		EntityTransaction transacao = gerente.getTransaction();

		try {
			transacao.begin();
			gerente.persist(fuxico);
			transacao.commit();
			gerente.close();
		} catch (Exception e) {
			e.getMessage();
		} finally {

		}

	}

	/*
	 * public List<Fuxico> consultarFuxicoPorLogin(String login) { EntityManager
	 * gerente = fabrica.createEntityManager(); try { Query consulta = gerente
	 * .createNamedQuery("consultarFuxicosPorLogin");
	 * consulta.setParameter("login", login); return consulta.getResultList(); }
	 * catch (NoResultException e) { return null; } finally { gerente.close(); }
	 * 
	 * }
	 */
}