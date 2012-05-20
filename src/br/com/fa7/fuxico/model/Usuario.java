package br.com.fa7.fuxico.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@SequenceGenerator(name="sequence", sequenceName="usuario_sequence", allocationSize=1)
public class Usuario implements Cloneable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull @Size(max = 255)
	private String nome;
	
	@NotNull @Size(max = 32)
	private String login;
	
	@NotNull @Size(max = 100)
	private String senha;
	
	@OneToMany 
	private Collection<Usuario> usuarios;
	
	@Size(max = 64) 
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario clone() {
		Usuario usuario = null;
		try {
			usuario = (Usuario) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}