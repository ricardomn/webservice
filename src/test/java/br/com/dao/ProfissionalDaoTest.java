package br.com.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.entity.Profissional;
import builders.CriadorDeProfissional;

public class ProfissionalDaoTest extends AbstractDbUnitTest{

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
	
	@Test
	public void deveSerPossivelDeletarUmProfissionalPassandoPassandoComoParametroOMesmo() throws Exception {
		Profissional profissional = new CriadorDeProfissional().addProfissional("PROFISSIONAL 1", "12345678910");
		dao.delete(profissional);
		assertEquals(3, dao.listAll().size());
	}
	
	@Test
	public void deveSerPossivelCarregarUmProfissionalQuandoPassarId() throws Exception {
		Profissional profissional = dao.findById(1L);
		assertNotNull(profissional);
		assertEquals("PROFISSIONAL 1", profissional.getNome());
	}
	
	@Test
	public void deveSerPossivelCarregarUmProfissionalAoPassarUmCpf() throws Exception {
		Profissional profissional = dao.findByCpf("12345678910");
		assertNotNull(profissional);
		assertEquals("PROFISSIONAL 1", profissional.getNome());
	}
	
	@Test(expected=Exception.class)
	public void deverSerPossivelLancarExceptionAoTentarGravarProfissionalSemCpf() throws Exception {
		Profissional profissional = new CriadorDeProfissional().addProfissional("PROFISSIONAL 4", null);
		dao.salvar(profissional);
	}
	
	@Test
	public void deveSerPossivelAtualizarUmProfissional() throws Exception {
		Profissional profissional = dao.findByCpf("12345678910");
		profissional.setNome("PROFISSIONAL ATUALIADO");
		dao.update(profissional);
		assertEquals("PROFISSIONAL ATUALIADO", dao.findByCpf("12345678910").getNome());
	}
	
	@Test
	public void deveSerPossivelRetornarNullQuandoNaoEncontrarUmProfissionalComOCpf() throws Exception {
		Profissional profissional = dao.findByCpf("12358354");
		assertNull(profissional);
	}
	
	@Test
	public void deveSerPossivelRetornarNullQuandoNaoEncontrarUmProfissionalPassandoIdInexistente() throws Exception {
		Profissional profissional = dao.findById(Long.MAX_VALUE);
		assertNull(profissional);
	}
	
	@Test(expected=Exception.class)
	public void deveSerPossivelRetornarExceptionQuandoTentarDeletarProfissionalInexistente() throws Exception {
		Profissional profissional = new CriadorDeProfissional().addProfissional("PROFISSIONAL 4", "452245");
		dao.delete(profissional);
	}
	
	@Test(expected=Exception.class)
	public void deveSerPossivelLancarUmaExceptionQuandoTentarCadastrarProfissionalComCpfMaiorQue11Caracteres() throws Exception {
		Profissional profissional = new CriadorDeProfissional().addProfissional("PROFISSIONAL 4", "123456789102");
		dao.salvar(profissional);
	}
}
