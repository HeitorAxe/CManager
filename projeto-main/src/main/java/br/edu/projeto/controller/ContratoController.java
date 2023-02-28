package br.edu.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.projeto.dao.ContratoDAO;
import br.edu.projeto.model.Contrato;

//@Model equivale a essas duas anotações
@RequestScoped
//Torna classe disponível na camada de visão (html)
@Named
public class ContratoController implements Serializable {

	@Inject
	//Mensagens de erro para o usuário
    private FacesContext facesContext;

    @Inject
    private ContratoDAO contratoDAO;

    private Contrato novoContrato;
	
    private List<Contrato> listaContratos;
    
    //Executa após instanciar classe ContratoController, ou seja, 
    //a cada requisição (RequestScoped)
    @PostConstruct
    public void inicializarContrato() {
        novoContrato = new Contrato();
        listaContratos = contratoDAO.findAll();
    }
    
    public void register() throws Exception {
    	//novoContrato.setData_criacao(novoContrato.getData_criacao_aux());
        try {
            contratoDAO.save(novoContrato);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            inicializarContrato();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
    }
    
    public void update()
    {
    	contratoDAO.update(novoContrato);
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public ContratoDAO getContratoDAO() {
		return contratoDAO;
	}

	public void setContratoDAO(ContratoDAO contratoDAO) {
		this.contratoDAO = contratoDAO;
	}

	public Contrato getNovoContrato() {
		return novoContrato;
	}

	public void setNovoContrato(Contrato novoContrato) {
		this.novoContrato = novoContrato;
	}

	public List<Contrato> getListaContratos() {
		if (listaContratos == null) {
			listaContratos = contratoDAO.findAll();
		}
		return listaContratos;
	}

	public void setListaContratos(List<Contrato> listaContratos) {
		this.listaContratos = listaContratos;
	}
    
    
}
