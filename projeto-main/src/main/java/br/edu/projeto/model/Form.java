package br.edu.projeto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "form")
public class Form {
	@NotNull
	@Id
	private String num_contrato;
	private java.util.Date data_vencimento;
	private int n_processo_admin;
	private String dotacao_orcamentaria;
	private java.util.Date prazo_vigencia;
	private String mod_licitatoria;
	private String categoria;
	private java.util.Date data_ass;
	private java.util.Date data_homologacao;
	
	
	private String objeto;
	@Min(value=0, message="Valor inválido")
	private double valor;
	private java.util.Date prazo_entrega;
	private String aditivo_contratual;
	@Min(value=0, message="Valor inválido") 
	private double saldo_contratual;
	private String indice_reajuste;
	
	
	public String getNum_contrato() {
		return num_contrato;
	}
	public void setNum_contrato(String num_contrato) {
		this.num_contrato = num_contrato;
	}
	public java.util.Date getData_vencimento() {
		return data_vencimento;
	}
	public void setData_vencimento(java.util.Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}
	public int getN_processo_admin() {
		return n_processo_admin;
	}
	public void setN_processo_admin(int n_processo_admin) {
		this.n_processo_admin = n_processo_admin;
	}
	public String getDotacao_orcamentaria() {
		return dotacao_orcamentaria;
	}
	public void setDotacao_orcamentaria(String dotacao_orcamentaria) {
		this.dotacao_orcamentaria = dotacao_orcamentaria;
	}
	public java.util.Date getPrazo_vigencia() {
		return prazo_vigencia;
	}
	public void setPrazo_vigencia(java.util.Date prazo_vigencia) {
		this.prazo_vigencia = prazo_vigencia;
	}
	public String getMod_licitatoria() {
		return mod_licitatoria;
	}
	public void setMod_licitatoria(String mod_licitatoria) {
		this.mod_licitatoria = mod_licitatoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public java.util.Date getData_ass() {
		return data_ass;
	}
	public void setData_ass(java.util.Date data_ass) {
		this.data_ass = data_ass;
	}
	public java.util.Date getData_homologacao() {
		return data_homologacao;
	}
	public void setData_homologacao(java.util.Date data_homologacao) {
		this.data_homologacao = data_homologacao;
	}
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public java.util.Date getPrazo_entrega() {
		return prazo_entrega;
	}
	public void setPrazo_entrega(java.util.Date prazo_entrega) {
		this.prazo_entrega = prazo_entrega;
	}
	public String getAditivo_contratual() {
		return aditivo_contratual;
	}
	public void setAditivo_contratual(String aditivo_contratual) {
		this.aditivo_contratual = aditivo_contratual;
	}
	public double getSaldo_contratual() {
		return saldo_contratual;
	}
	public void setSaldo_contratual(double saldo_contratual) {
		this.saldo_contratual = saldo_contratual;
	}
	public String getIndice_reajuste() {
		return indice_reajuste;
	}
	public void setIndice_reajuste(String indice_reajuste) {
		this.indice_reajuste = indice_reajuste;
	}
	
	
	
}
