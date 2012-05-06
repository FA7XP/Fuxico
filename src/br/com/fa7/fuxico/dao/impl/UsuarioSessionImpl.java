package br.com.fa7.fuxico.dao.impl;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.fa7.fuxico.dao.UsuarioSession;
import br.com.fa7.fuxico.model.Usuario;

@SuppressWarnings("serial")
@Component @SessionScoped
public class UsuarioSessionImpl implements Serializable, UsuarioSession {

	private HttpSession session;
	private int segundosEmUmDia = 86400;

	public UsuarioSessionImpl( HttpSession session ) {
		this.session = session;
		this.session.setMaxInactiveInterval( segundosEmUmDia );
	}

	public Usuario getUsuario() {
		return (Usuario) session.getAttribute("userSession");
	}

	public void setUsuario( Usuario usuario ) {
		this.session.setAttribute("userSession", usuario);
	}
}