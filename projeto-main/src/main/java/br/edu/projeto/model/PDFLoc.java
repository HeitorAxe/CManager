package br.edu.projeto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "c_pdf")
public class PDFLoc {

	@NotNull
	private String titulo;
	@NotNull
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String loc;
	@NotNull
	private int num_contrato;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getNum_contrato() {
		return num_contrato;
	}
	public void setNum_contrato(int num_contrato) {
		this.num_contrato = num_contrato;
	}
	
	
	
	
}
