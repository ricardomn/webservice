package br.com.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.CriadorDeProfissional;

public class ProfissionalTest {

	@Test
	public void naoDeveSerPossivelProfissionaisComCpfMenorQueOnzeCaracteres() {
		Profissional profissional = new CriadorDeProfissional().addProfissional("PROFISSIONAL 1", "1234");
		assertEquals(false, profissional.isCpfValido());
	}
	
	@Test
	public void naoDeveSerPossivelProfissionaisComCpfMaiorQue11Caracteres() throws Exception {
		Profissional profissional = new CriadorDeProfissional().addProfissional("PROFISSIONAL 1", "123456789102");
		assertEquals(false, profissional.isCpfValido());
	}

	@Test
	public void deveSerPossivelProfissionaisComCpfComCpfComOnzeCaracteress() throws Exception {
		Profissional profissional = new CriadorDeProfissional().addProfissional("PROFISSIONAL 1", "12345678910");
		assertEquals(true, profissional.isCpfValido());
	}
	
	@Test
	public void naoDeveSerPossivelProfissionaisComCpfNulosOuVazio() throws Exception {
		Profissional profissional1 = new CriadorDeProfissional().addProfissional("PROFISSIONAL 1", "");
		assertEquals(false, profissional1.isCpfValido());
		
		Profissional profissional2 = new CriadorDeProfissional().addProfissional("PROFISSIONAL 1", null);
		assertEquals(false, profissional2.isCpfValido());
	}
}
