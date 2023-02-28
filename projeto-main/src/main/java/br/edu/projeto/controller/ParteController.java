package br.edu.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.projeto.dao.ParteDAO;
import br.edu.projeto.model.Parte;


//@Model equivale a essas duas anotações
@RequestScoped
//Torna classe disponível na camada de visão (html)
@Named
public class ParteController implements Serializable {
	@Inject
	//Mensagens de erro para o usuário
    private FacesContext facesContext;

    @Inject
    private ParteDAO parteDAO;

    private Parte novaParte;
	
    private List<Parte> listaPartes;
    
    //Executa após instanciar classe parteController, ou seja, 
    //a cada requisição (RequestScoped)
    @PostConstruct
    public void inicializarparte() {
        novaParte = new Parte();
        listaPartes = parteDAO.findAll();
    }
    
    public void register() throws Exception {
    	//novoparte.setData_criacao(novoparte.getData_criacao_aux());
        try {
            parteDAO.save(novaParte);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            inicializarparte();
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

	public ParteDAO getParteDAO() {
		return parteDAO;
	}

	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}

	public Parte getNovaParte() {
		return novaParte;
	}

	public void setNovaParte(Parte novaParte) {
		this.novaParte = novaParte;
	}

	public List<Parte> getListaPartes() {
		if (listaPartes == null) {
			listaPartes = parteDAO.findAll();
		}
		return listaPartes;
	}

	public void setListaPartes(List<Parte> listaPartes) {
		this.listaPartes = listaPartes;
	}
    
    
}
