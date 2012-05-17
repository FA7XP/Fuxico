package br.com.fa7.fuxico.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name="sequence", sequenceName="fuxico_sequence", allocationSize=1)
public class Fuxico implements Cloneable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull 
	@ManyToOne
	private Usuario usuario;
	
	@NotNull @Size(max = 255)
	private String fuxico;
	
	@NotNull  
	private Date data;
	
	@Transient
	private String dataFormatada;
	
	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = new SimpleDateFormat("MM/yy hh:mm:ss").format(new Date());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFuxico() {
		return fuxico;
	}

	public void setFuxico(String fuxico) {
		this.fuxico = fuxico;
	}

	public String getData() {
		return new SimpleDateFormat("MM/yy hh:mm:ss").format(data);
	}
	
	public String getDataFormatada() {
		return this.dataFormatada;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Fuxico clone() {
		try {
			return (Fuxico) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}