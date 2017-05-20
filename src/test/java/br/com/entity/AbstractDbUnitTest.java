package br.com.entity;

import java.io.InputStream;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import br.com.dao.ProfissionalDao;

public abstract class AbstractDbUnitTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private static IDatabaseConnection connection;
	private static IDataSet dataset;
	protected static EntityManager entityManager;
	protected static ProfissionalDao dao;
	
	@BeforeClass
	public static void initEntityManager() throws HibernateException, DatabaseUnitException{
		entityManagerFactory = Persistence.createEntityManagerFactory("HqsqlDbPUTest");
		entityManager = entityManagerFactory.createEntityManager();
		connection = new DatabaseConnection(((SessionImpl) (entityManager.getDelegate())).connection());
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
		dao = new ProfissionalDao(entityManager);
		
		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		InputStream dataSet = Thread.currentThread().getContextClassLoader().getResourceAsStream("profissional.xml");
		dataset = flatXmlDataSetBuilder.build(dataSet);
	}
	
	@AfterClass
	public  static void closeEntityManager(){
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Before
	public void setUp() throws DatabaseUnitException, SQLException{
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException{
		DatabaseOperation.DELETE_ALL.execute(connection, dataset);
	}
}
