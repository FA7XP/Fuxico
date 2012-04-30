package br.com.fa7.fuxico.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.fa7.fuxico.dao.UsuarioDao;
import br.com.fa7.fuxico.model.Usuario;

@Component @RequestScoped
public class UsuarioDaoImpl extends GenericDaoImpl implements UsuarioDao {


	@SuppressWarnings("unchecked")
	public List<Usuario> list() {
		return criteriosDeBuscaDeUsuario().list();
	}

	public boolean isLoginExiste( String login ) {
		Criteria criteria = getSession().createCriteria(Usuario.class, "user");
		
		criteria.add(Restrictions.eq("user.login", login.toLowerCase()));

		ProjectionList list = Projections.projectionList().create();
		list.add(Projections.property("user.login"), "login");
		criteria.setProjection(list).setResultTransformer(new AliasToBeanResultTransformer(Usuario.class));

		return ( criteria.uniqueResult() != null );
	}

	public Usuario login( String login, String senha ) {
		try {
			Criteria criteria = getSession().createCriteria(Usuario.class, "user");
			criteria.add(Restrictions.eq("user.login", login));
			criteria.add(Restrictions.eq("user.senha", senha));
			return (Usuario) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao tenatar efetuar Login");
		}
	}

	private Criteria criteriosDeBuscaDeUsuario() {
		Criteria criteria = getSession().createCriteria(Usuario.class, "u");
		criteria.addOrder(Order.asc("u.nome"));

		return (Criteria) criteria.list(); 
	}
}