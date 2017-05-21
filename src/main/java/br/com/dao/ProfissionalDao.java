package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.entity.Profissional;
import br.com.util.JPAUtil;

public class ProfissionalDao {
	
	private EntityManager entityManager;
	
	public ProfissionalDao(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void salvar(Profissional profissional) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(profissional);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		
	}
	
	public Profissional findById(Long id){
		Profissional profissional =  entityManager.find(Profissional.class, id);
		return profissional;
	}
	
	public Profissional findByCpf(String cpf){
		String jpql = "SELECT p FROM Profissional p WHERE p.cpf = :cpf";
		TypedQuery<Profissional> query = entityManager.createQuery(jpql, Profissional.class);
		query.setParameter("cpf", cpf);
		try{
			Profissional profissional = query.getSingleResult();
			return profissional;
		}catch(NoResultException e) {
			return null;
		}
		
	}
	
	public Profissional update(Profissional profissional) {
		entityManager.getTransaction().begin();
		Profissional profissionalMerge =  	entityManager.merge(profissional);
		entityManager.getTransaction().commit();
		return profissionalMerge;
	}
	
	public void delete(Profissional profissional) throws Exception{
		
		Profissional prof = findByCpf(profissional.getCpf());
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(prof);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Profissional> listAll(){
		String jqpl = "SELECT p FROM Profissional p ORDER BY p.nome";
		TypedQuery<Profissional> query = entityManager.createQuery(jqpl, Profissional.class);
		List<Profissional> lista = query.getResultList();
		return lista;
	}
}
