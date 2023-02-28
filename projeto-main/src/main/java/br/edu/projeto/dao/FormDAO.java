package br.edu.projeto.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.projeto.model.Form;

@Stateful
public class FormDAO {
	//Criar os objetos sob demanda de forma automática
		@Inject
	    private EntityManager em;

		public Form findById(Long id) {
	        return em.find(Form.class, id);   
	    }
		
	//	public Form findByNumContrato(String num_contrato) {
	//        return em.find(Form.class, num_contrato);
	        
	//    }
		
		public List<Form> findAll(){
			//Cria objeto que fará consulta
			CriteriaBuilder cb = em.getCriteriaBuilder();
			//Retorno é da classe Form
	        CriteriaQuery<Form> criteria = cb.createQuery(Form.class);
	        //From usuario
	        Root<Form> form = criteria.from(Form.class);
	        //Select todos os usuários
	        criteria.select(form);//.orderBy(cb.asc(form.get("titulo")));
	        //Executa a consulta e traz o retorno
	        return em.createQuery(criteria).getResultList();
		}
		
		public List<Form> findAllHQL() {
		    return em.createQuery("SELECT a FROM form a ", 
		    		Form.class).getResultList();      
		}
		
		public List<Form>findByNumContrato(String num_contrato) {
			List<Form> result= em.createQuery("SELECT a FROM Form a WHERE a.num_contrato='"+num_contrato+"'", Form.class).getResultList();
			
			return result;
			
		} 
		
		public List<Form>findByExpireDate() {
			
			java.util.Date today = new java.util.Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.MONTH, 2);
			java.util.Date modifiedDate = cal.getTime();
		
			List<Form> output = new ArrayList<Form>();
			
			List<Form> result= em.createQuery("SELECT a FROM Form a ", Form.class).getResultList();
			
			for(int i = 0; i<result.size();i++)
			{
				if(result.get(i).getData_vencimento()!=null){
					if(result.get(i).getData_vencimento().compareTo(today)>0)
					{
						if(modifiedDate.compareTo(result.get(i).getData_vencimento())>0)
						{
							output.add(result.get(i));
						}
					}
				}
			}
			
			return output;
			
		} 
		
		public void save(Form c) {
			em.persist(c);
		}
		
		public void update(Form u) {
			em.merge(u);
		}
		
		public void excluir(Form u) {
			em.remove(em.getReference(Form.class, u.getNum_contrato()));
		}
}
