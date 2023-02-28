package br.edu.projeto.dao;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Contrato;

@Stateful
public class ContratoDAO{

	//Criar os objetos sob demanda de forma automática
	@Inject
    private EntityManager em;

	public Contrato findById(String id) {
        return em.find(Contrato.class, id);
        
    }
	
	public List<Contrato> findAll(){
		//Cria objeto que fará consulta
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//Retorno é da classe Contrato
        CriteriaQuery<Contrato> criteria = cb.createQuery(Contrato.class);
        //From usuario
        Root<Contrato> contrato = criteria.from(Contrato.class);
        //Select todos os usuários
        criteria.select(contrato);//.orderBy(cb.asc(contrato.get("titulo")));
        //Executa a consulta e traz o retorno
        return em.createQuery(criteria).getResultList();
	}
	
	public List<Contrato> findAllHQL() {
	    return em.createQuery("SELECT a FROM contrato a ", 
	    		Contrato.class).getResultList();      
	}
	
	
	public void save(Contrato c) {
		em.persist(c);
	}
	
	public void update(Contrato u) {
		em.merge(u);
	}
	
	public void excluir(String id) {
		em.remove(em.getReference(Contrato.class, id));
	}
	
}
