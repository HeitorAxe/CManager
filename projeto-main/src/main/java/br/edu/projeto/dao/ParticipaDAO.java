package br.edu.projeto.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.IdClass;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Parte;
import br.edu.projeto.model.Participa;

@Stateful
public class ParticipaDAO {
	@Inject
    private EntityManager em;

	public Participa findById(String num_contrato) {
        return em.find(Participa.class, num_contrato);
        
    }
	
	public List<Participa> findAll(){
		//Cria objeto que fará consulta
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//Retorno é da classe Participa
        CriteriaQuery<Participa> criteria = cb.createQuery(Participa.class);
        //From usuario
        Root<Participa> participa = criteria.from(Participa.class);
        //Select todos os usuários
        criteria.select(participa);//.orderBy(cb.asc(participa.get("titulo")));
        //Executa a consulta e traz o retorno
        return em.createQuery(criteria).getResultList();
	}
	
	public List<Participa> findAllHQL() {
	    return em.createQuery("SELECT a FROM participa a ", 
	    		Participa.class).getResultList();      
	}

	public List<Participa>findByNumContrato(String num_contrato) {
		List<Participa> result= em.createQuery("SELECT a FROM Participa a WHERE a.num_contrato='"+num_contrato+"'", Participa.class).getResultList();
		
		return result;
		
	}
	
	public List<Participa>findByCNPJ(String cnpj) {
		List<Participa> result= em.createQuery("SELECT a FROM Participa a WHERE a.cnpj_parte='"+cnpj+"'", Participa.class).getResultList();
		
		return result;
		
	}
	
	
	public void save(Participa p) {
		em.persist(p);
	}

	public void excluirByNumContrato(String numContrato) {
		List<Participa> result = this.findByNumContrato(numContrato);
	   	for(int i=0; i<result.size();i++)
    	{
	   		em.remove(result.get(i));
    	}
	}
	
	public void excluirByCNPJ(String cnpj) {
		List<Participa> result = this.findByCNPJ(cnpj);
	   	for(int i=0; i<result.size();i++)
    	{
	   		em.remove(result.get(i));
    	}
	}
}
