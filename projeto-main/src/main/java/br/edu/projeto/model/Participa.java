package br.edu.projeto.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Participa")
public class Participa implements Serializable{
	@NotNull
	@Id
	private String cnpj_parte;
	@NotNull
	@Id
	private String num_contrato;
	
	public String getCnpj_parte() {
		return cnpj_parte;
	}
	public void setCnpj_parte(String cnpj_parte) {
		this.cnpj_parte = cnpj_parte;
	}
	public String getNum_contrato() {
		return num_contrato;
	}
	public void setNum_contrato(String num_contrato) {
		this.num_contrato = num_contrato;
	}
	
	
	
}
