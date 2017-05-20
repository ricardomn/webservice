package br.com.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class ProfissionalTest extends AbstractDbUnitTest{

	@Test
	public void deveSerPossivelEncontrarProfissionalQuandoPassarParametroId() {
		
		Profissional profissional = dao.findById(1L);
		assertNotNull(profissional);
		assertEquals("PROFISSIONAL 1", profissional.getNome());
	}

	@Test
	public void deveSerPossivelRetonarUmaListaQuandoExecutarOMetodoListAll() throws Exception {
		List<Profissional> lista = dao.listAll();
		assertEquals(4, lista.size());
		assertEquals("PROFISSIONAL 2", lista.get(1).getNome());
	}
	
}
