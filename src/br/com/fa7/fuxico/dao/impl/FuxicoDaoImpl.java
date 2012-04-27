package br.com.fa7.fuxico.dao.impl;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.fa7.fuxico.dao.FuxicoDao;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.model.Fuxico;

@Component @RequestScoped
public class FuxicoDaoImpl extends GenericDaoImpl implements FuxicoDao {
		
	@SuppressWarnings("unchecked")
	public List<Fuxico> list() {
		return criteriosDeBuscaDeFuxico().list();
	}

	@SuppressWarnings("unchecked")
	public List<Fuxico> search( Long filtro, Long unidade, String search, Long empresaId ) {
		Criteria criteria = criteriosDeBuscaDeFuxico();
			//criteria.add(Restrictions.eq("user.empresa.id", empresaId));
		//if( unidade != 0 ) criteria.add(Restrictions.eq("uni.id", unidade));

//		if( search.equals("todos") ) return criteria.list();
//
	//	if( filtro == 1 ) criteria.add(Restrictions.ilike("user.nome", "%" + search + "%"));
		//else criteria.add(Restrictions.ilike("user.placa", "%" + search + "%"));

		return criteria.list();
	}

	public Fuxico load( Long id ) {
		return (Fuxico) getSession().load(Fuxico.class, id);
	}

	public Fuxico load( String login ) {
		return (Fuxico) getSession().load(Fuxico.class, login);
	}

	public boolean isLoginExiste( String login ) {
		Criteria criteria = getSession().createCriteria(Fuxico.class, "fuxico");
			criteria.add(Restrictions.eq("fuxico.usuario_id", login.toLowerCase()));
			//criteria.add(Restrictions.eq("fuxico.ativo", true));
		ProjectionList list = Projections.projectionList().create();
			list.add(Projections.property("fuxico.usuario_id"), "usuario_id");
		criteria.setProjection(list).setResultTransformer(new AliasToBeanResultTransformer(Fuxico.class));

		return ( criteria.uniqueResult() != null );
	}

	public Fuxico login( String login, String senha ) {
		try {
			Criteria criteria = getSession().createCriteria(Fuxico.class, "fuxico");
				criteria.add(Restrictions.eq("fuxico.usuario_id", true));
				criteria.add(Restrictions.eq("user.login", login));
				criteria.add(Restrictions.eq("user.senha", senha));
			return (Fuxico) criteria.uniqueResult();
		} catch(NonUniqueResultException e) {
			e.printStackTrace();
			throw new RuntimeException("Usuário duplicado no sistema.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível acessar o sistema.");
		}
	}

	private Criteria criteriosDeBuscaDeFuxico() {
		Criteria criteria = getSession().createCriteria(Fuxico.class, "fuxico");
			criteria.createCriteria("fuxico.usuario_id", "usuario");
			criteria.createCriteria("fuxico.data", "data");
			criteria.createCriteria("fuxico.fuxico", "fuxico");
			
		//return criteria.add(Restrictions.eq("user.ativo", true)).addOrder(Order.asc("user.nome"));
        return criteria;			
	}

	
}