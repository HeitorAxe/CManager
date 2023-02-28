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
public class MainPageController implements Serializable {
	private String numAtual;
    @Inject
    private ContratoDAO contratoDAO;
    private Contrato contratoAtual;
    @Inject
    private FormDAO formDAO;
    private Form formAtual;
    private List<Form> expiringForms;
    @Inject
    private ParticipaDAO participaDAO;
    private List<Participa> listaParteAtual;
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
        contratoAtual = new Contrato();
        //Provisório, o ideal é ter
        //um elemento que indica se a 
        //pagina ta em estado de cadastro
        this.contratoAtual.setTitulo("Cadastro");
        listaParteAtual = participaDAO.findByNumContrato(numAtual);
        expiringForms = formDAO.findByExpireDate();
        formAtual = new Form();
    }
    
    public void goToPartes() throws IOException{
		facesContext.getExternalContext().redirect("partes.xhtml");
    }
    public void goToCadastro() throws IOException{
		facesContext.getExternalContext().redirect("cadastro_usuario.xhtml");
    }
    
    public void updateForm()
    {
    	
    	formDAO.update(formAtual);
    }

    public void deleteContrato(){
    	
    	participaDAO.excluirByNumContrato(formAtual.getNum_contrato());
    	if(formDAO.findByNumContrato(formAtual.getNum_contrato()).size()!=0){
    		formDAO.excluir(formAtual);
    	}
    	contratoDAO.excluir(formAtual.getNum_contrato());

    }
    
	public String getNumAtual() {
		return numAtual;
	}

	public void setNumAtual(String numAtual) {
		this.numAtual = numAtual;
	}

	public ContratoDAO getContratoDAO() {
		return contratoDAO;
	}

	public void setContratoDAO(ContratoDAO contratoDAO) {
		this.contratoDAO = contratoDAO;
	}

	public Contrato getContratoAtual() {
		return contratoAtual;
	}

	public void setContratoAtual(Contrato contratoAtual) {
		this.contratoAtual = contratoAtual;
	}

	public void updateContratoAtual()
	{
		
		if(formDAO.findByNumContrato(numAtual).size()!=0) {
			this.setFormAtual(formDAO.findByNumContrato(numAtual).get(0));
		}
		this.setContratoAtual(contratoDAO.findById(numAtual));
		this.setListaParteAtual(participaDAO.findByNumContrato(numAtual));
	}

	public FormDAO getFormDAO() {
		return formDAO;
	}

	public void setFormDAO(FormDAO formDAO) {
		this.formDAO = formDAO;
	}

	public Form getFormAtual() {
		return formAtual;
	}

	public void setFormAtual(Form formAtual) {
		this.formAtual = formAtual;
	}

	public ParticipaDAO getParticipaDAO() {
		return participaDAO;
	}

	public void setParticipaDAO(ParticipaDAO participaDAO) {
		this.participaDAO = participaDAO;
	}

	public List<Participa> getListaParteAtual() {
		return listaParteAtual;
	}

	public void setListaParteAtual(List<Participa> listaParteAtual) {
		this.listaParteAtual = listaParteAtual;
	}

	public List<Form> getExpiringForms() {
		return expiringForms;
	}

	public void setExpiringForms(List<Form> expiringForms) {
		this.expiringForms = expiringForms;
	}

	

	
	

	
	
	
}
