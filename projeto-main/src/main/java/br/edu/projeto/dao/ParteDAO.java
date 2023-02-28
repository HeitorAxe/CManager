package br.edu.projeto.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Contrato;
import br.edu.projeto.model.Form;
import br.edu.projeto.model.Parte;

@Stateful
public class ParteDAO {
	@Inject
    private EntityManager em;

	public Parte findById(String cnpj) {
        return em.find(Parte.class, cnpj);  
    }
	
	public List<Parte> findAll(){
		//Cria objeto que fará consulta
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//Retorno é da classe Parte
        CriteriaQuery<Parte> criteria = cb.createQuery(Parte.class);
        //From usuario
        Root<Parte> Parte = criteria.from(Parte.class);
        //Select todos os usuários
        criteria.select(Parte);//.orderBy(cb.asc(Parte.get("titulo")));
        //Executa a consulta e traz o retorno
        return em.createQuery(criteria).getResultList();
	}
	
	
	public List<Parte> findAllHQL() {
	    return em.createQuery("SELECT a FROM parte a ", 
	    		Parte.class).getResultList();      
	}
	
	public void save(Parte c) {
		em.persist(c);
	}
	
	public void update(Parte u) {
		em.merge(u);
	}
	
	public void excluir(String id) {
		em.remove(em.getReference(Parte.class, id));
	}
}
