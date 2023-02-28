package br.edu.projeto.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Contrato;
import br.edu.projeto.model.PDFLoc;

@Stateful
public class PDFLocDAO {
	@Inject
    private EntityManager em;

	public PDFLoc findById(String loc) {
        return em.find(PDFLoc.class, loc);
    }
	
	public List<PDFLoc> findAll(){
		//Cria objeto que fará consulta
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//Retorno é da classe Contrato
        CriteriaQuery<PDFLoc> criteria = cb.createQuery(PDFLoc.class);
        //From usuario
        Root<PDFLoc> pdfLoc = criteria.from(PDFLoc.class);
        //Select todos os usuários
        criteria.select(pdfLoc);//.orderBy(cb.asc(pdfLoc.get("titulo")));
        //Executa a consulta e traz o retorno
        return em.createQuery(criteria).getResultList();
	}

	public List<PDFLoc> findAllHQL() {
	    return em.createQuery("SELECT a FROM c_pdf a ", 
	    		PDFLoc.class).getResultList();      
	}

	public void save(PDFLoc l) {
		em.persist(l);
	}
	
}
