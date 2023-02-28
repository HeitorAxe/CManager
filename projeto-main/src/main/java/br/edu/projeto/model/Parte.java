package br.edu.projeto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "parte")
public class Parte {

	@NotNull
	@NotEmpty(message="Não pode estar vazio")
	private String nome;
	@NotNull
	@Id
	@NotEmpty(message="Não pode estar vazio")
	private String cnpj;
	@Email(message="Deve ser um Email Válido")
	private String email;
	@Pattern(regexp="(^$|[0-9]{10})", message="Deve ser um número válido. Exemplo: 8323140739")
	private String telefone;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
}
