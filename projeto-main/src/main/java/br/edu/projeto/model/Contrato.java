package br.edu.projeto.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contrato")
public class Contrato {
	
	@NotNull
	private java.util.Date data_criacao;
	@Column(name = "titulo")
	@NotNull
	@NotEmpty(message="Não pode estar vazio")
	private String titulo;
	@Column(name = "status")
	@NotNull
	@NotEmpty(message="Não pode estar vazio")
	private String status;
	@Column(name = "num_contrato")
	@Id
	@NotNull
	@NotEmpty(message="Não pode estar vazio")
	private String id;
	
	public void setData_criacao(java.util.Date data_criacao) {
		this.data_criacao = data_criacao;
	}


	public java.util.Date getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(String data_criacao) throws ParseException {
		this.data_criacao = new SimpleDateFormat("dd/MM/yyyy").parse(data_criacao);  
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
