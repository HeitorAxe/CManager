package br.edu.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.projeto.dao.ParticipaDAO;
import br.edu.projeto.model.Participa;
@RequestScoped
@Named
public class ParticipaController implements Serializable{
	@Inject
	//Mensagens de erro para o usuário
    private FacesContext facesContext;

    @Inject
    private ParticipaDAO participaDAO;

    private Participa novoParticipa;
	
    private List<Participa> listaParticipas;
    
    //Executa após instanciar classe ParticipaController, ou seja, 
    //a cada requisição (RequestScoped)
    @PostConstruct
    public void inicializarParticipa() {
        this.novoParticipa = new Participa();
        this.listaParticipas = participaDAO.findAll();
    }
    
    public void register() throws Exception {
    	//novoParticipa.setData_criacao(novoParticipa.getData_criacao_aux());
        try {
            participaDAO.save(novoParticipa);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            inicializarParticipa();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
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

	public ParticipaDAO getParticipaDAO() {
		return participaDAO;
	}

	public void setParticipaDAO(ParticipaDAO participaDAO) {
		this.participaDAO = participaDAO;
	}

	public Participa getNovoParticipa() {
		return novoParticipa;
	}

	public void setNovoParticipa(Participa novoParticipa) {
		this.novoParticipa = novoParticipa;
	}

	public List<Participa> getListaParticipas() {
		if (listaParticipas == null) {
			listaParticipas = participaDAO.findAll();
		}
		return listaParticipas;
	}

	public void setListaParticipas(List<Participa> listaParticipas) {
		this.listaParticipas = listaParticipas;
	}
}
