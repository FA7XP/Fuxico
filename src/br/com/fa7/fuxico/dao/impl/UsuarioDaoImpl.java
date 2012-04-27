package br.com.fa7.fuxico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

	private EntityManagerFactory fabrica;

	public UsuarioDaoImpl() {
		fabrica = Persistence.createEntityManagerFactory("fuxico");
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> list() {
		return criteriosDeBuscaDeUsuario().list();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> search( Long filtro, Long unidade, String search, Long empresaId ) {
		Criteria criteria = criteriosDeBuscaDeUsuario();
			criteria.add(Restrictions.eq("user.empresa.id", empresaId));
		if( unidade != 0 ) criteria.add(Restrictions.eq("uni.id", unidade));

		if( search.equals("todos") ) return criteria.list();

		if( filtro == 1 ) criteria.add(Restrictions.ilike("user.nome", "%" + search + "%"));
		else criteria.add(Restrictions.ilike("user.placa", "%" + search + "%"));

		return criteria.list();
	}

	public List<Usuario> aniversariantes( Long empresaId ) {
		return null;
	}

	public Usuario load( Long id ) {
		return (Usuario) getSession().load(Usuario.class, id);
	}

	public Usuario load( String login ) {
		return (Usuario) getSession().load(Usuario.class, login);
	}

	public boolean isLoginExiste( String login ) {
		Criteria criteria = getSession().createCriteria(Usuario.class, "user");
			criteria.add(Restrictions.eq("user.login", login.toLowerCase()));
			criteria.add(Restrictions.eq("user.ativo", true));
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
		} catch(NonUniqueResultException e) {
			e.printStackTrace();
			throw new RuntimeException("Usuário duplicado no sistema.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível acessar o sistema.");
		}
	}

	private Criteria criteriosDeBuscaDeUsuario() {
		Criteria criteria = getSession().createCriteria(Usuario.class, "u");

		criteria.createCriteria("u.nome", "nome");
		criteria.add(Restrictions.eq("u.nome", "samuel"));		
		criteria.addOrder(Order.asc("u.nome"));

		return (Criteria) criteria.list(); 
	}
	
	public Boolean verificarLoginExiste(String login) {
		Criteria criteria = getSession().createCriteria(Usuario.class, "usuario");
			criteria.createCriteria("usuario.login", "usuario");
			criteria.add(Restrictions.eq("usuario.login", login));
        return !criteria.list().isEmpty();			
	}
	
	public List<Usuario> consultarTodosUsuarios() {
		EntityManager gerente = fabrica.createEntityManager();
		Query consulta = gerente.createQuery("select a from Usuario a");
		return consulta.getResultList();

	}
	
	public Usuario consultarUsuarioPorLogin(String login) {
		EntityManager gerente = fabrica.createEntityManager();
		try {
			Query consulta = gerente
					.createNamedQuery("consultarUsuarioPorLogin");
			consulta.setParameter("login", login);
			return (Usuario) consulta.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			gerente.close();
		}

	}
}