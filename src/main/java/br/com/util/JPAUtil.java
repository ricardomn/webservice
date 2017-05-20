package br.com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final String PERSISTENCE_UNIT_NAME = "HqsqlDbPU";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		} catch (javax.persistence.PersistenceException ex) {
			System.out.println("Não foi possível conectar ao banco: " + ex);
		}
	}
	
	public static EntityManager getEntityManager() {
		if (emf != null && !emf.isOpen()) {
			throw new RuntimeException("EntityManagerFactory está fechada!");
		}
		if (em == null) {
			try {
				em = emf.createEntityManager();
				System.out.println("Conexão aberta!");
			} catch (javax.persistence.PersistenceException ex) {
				System.out.println("Erro ao gerar a conexão com o banco");
			}
		}
		return em;
	}

	public static void closeEntityManagerFactory() {

		if (em != null && em.isOpen()) {
			em.close();
			System.out.println("Conexão fechada!");
		}

	}
}
