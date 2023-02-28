package br.edu.projeto.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.projeto.dao.ContratoDAO;
import br.edu.projeto.dao.FormDAO;
import br.edu.projeto.dao.ParteDAO;
import br.edu.projeto.dao.ParticipaDAO;
import br.edu.projeto.model.Contrato;
import br.edu.projeto.model.Form;
import br.edu.projeto.model.Parte;
import br.edu.projeto.model.Participa;

@RequestScoped
@Named
public class PartesPageController implements Serializable {
	private String numAtual;
    @Inject
    private ParteDAO parteDAO;
    @Inject
    private ParticipaDAO participaDAO;

    private Parte parteAtual;
    @Inject
    private FacesContext facesContext;
	
    @PostConstruct
    public void inicializa() {
    	//Verifica se usuário está autenticado e possui a permissão adequada
    	if (!this.facesContext.getExternalContext().isUserInRole("ADMINISTRADOR")) {
    		try {
				this.facesContext.getExternalContext().redirect("login-error.xhtml");
			} catch (IOException e) {e.printStackTrace();}
    	}
        parteAtual = new Parte();
        //Provisório, o ideal é ter
        //um elemento que indica se a 
        //pagina ta em estado de cadastro
        this.parteAtual.setCnpj("qwe11");
    }
    
    public void goToContratos() throws IOException{
		facesContext.getExternalContext().redirect("contratos.xhtml");
    }
    
    public void updateParte()
    {
    	
    	parteDAO.update(parteAtual);
    }
    
    public void deleteParte()
    {
    	participaDAO.excluirByCNPJ(parteAtual.getCnpj());
    	parteDAO.excluir(parteAtual.getCnpj());
    }
    
	public String getNumAtual() {
		return numAtual;
	}

	public void setNumAtual(String numAtual) {
		this.numAtual = numAtual;
	}


	public void updateParteAtual()
	{
		this.setParteAtual(parteDAO.findById(numAtual));
	}

	public ParteDAO getParteDAO() {
		return parteDAO;
	}

	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}

	public Parte getParteAtual() {
		return parteAtual;
	}

	public void setParteAtual(Parte parteAtual) {
		this.parteAtual = parteAtual;
	}
	



	
	

	
	
	
}
