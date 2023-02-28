package br.edu.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.projeto.dao.FormDAO;
import br.edu.projeto.model.Form;

//@Model equivale a essas duas anotações
@RequestScoped
//Torna classe disponível na camada de visão (html)
@Named
public class FormController implements Serializable {

	@Inject
	//Mensagens de erro para o usuário
    private FacesContext facesContext;

    @Inject
    private FormDAO formDAO;

    private Form novoForm;
	
    private List<Form> listaForms;
    
    //Executa após instanciar classe FormController, ou seja, 
    //a cada requisição (RequestScoped)
    @PostConstruct
    public void inicializarForm() {
        novoForm = new Form();
        listaForms = formDAO.findAll();
    }
    
    public void register() throws Exception {
    	//novoForm.setData_criacao(novoForm.getData_criacao_aux());
        try {
            formDAO.save(novoForm);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            inicializarForm();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
    }

    public void update()
    {
    	formDAO.update(novoForm);
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

	public FormDAO getFormDAO() {
		return formDAO;
	}

	public void setFormDAO(FormDAO formDAO) {
		this.formDAO = formDAO;
	}

	public Form getNovoForm() {
		return novoForm;
	}

	public void setNovoForm(Form novoForm) {
		this.novoForm = novoForm;
	}

	public List<Form> getListaForms() {
		if (listaForms == null) {
			listaForms = formDAO.findAll();
		}
		return listaForms;
	}

	public void setListaForms(List<Form> listaForms) {
		this.listaForms = listaForms;
	}
    
    
    
    
    
}
