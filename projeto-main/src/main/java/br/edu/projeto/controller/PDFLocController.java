package br.edu.projeto.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

import br.edu.projeto.dao.PDFLocDAO;
import br.edu.projeto.model.PDFLoc;

//@Model equivale a essas duas anotações
@RequestScoped
//Torna classe disponível na camada de visão (html)
@Named
public class PDFLocController implements Serializable {

	@Inject
	//Mensagens de erro para o usuário
    private FacesContext facesContext;
    @Inject
	private PDFLocDAO pdfLocDAO;
	private PDFLoc novoPDFLoc;
	private List<PDFLoc> listaPDFLoc;
	
    @PostConstruct
    public void inicializarPDFLoc() {
        novoPDFLoc = new PDFLoc();
        listaPDFLoc = pdfLocDAO.findAll();
    }

    
    public void register() throws Exception {
    	//novoContrato.setData_criacao(novoContrato.getData_criacao_aux());
        try {
            pdfLocDAO.save(novoPDFLoc);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            inicializarPDFLoc();
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


	public PDFLocDAO getPdfLocDAO() {
		return pdfLocDAO;
	}


	public void setPdfLocDAO(PDFLocDAO pdfLocDAO) {
		this.pdfLocDAO = pdfLocDAO;
	}


	public PDFLoc getNovoPDFLoc() {
		return novoPDFLoc;
	}


	public void setNovoPDFLoc(PDFLoc novoPDFLoc) {
		this.novoPDFLoc = novoPDFLoc;
	}


	public List<PDFLoc> getListaPDFLoc() {
		return listaPDFLoc;
	}


	public void setListaPDFLoc(List<PDFLoc> listaPDFLoc) {
		this.listaPDFLoc = listaPDFLoc;
	}
    
	
}
