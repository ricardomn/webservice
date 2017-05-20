package br.com.main;

import javax.persistence.EntityManager;

import br.com.dao.ProfissionalDao;
import br.com.util.JPAUtil;

public class ProfissionalTeste {
	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		ProfissionalDao dao = new ProfissionalDao(entityManager);
		System.out.println((dao.listAll()));
		
	}
}
