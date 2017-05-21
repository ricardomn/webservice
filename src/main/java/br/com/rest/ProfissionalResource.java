package br.com.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.dao.ProfissionalDao;
import br.com.entity.Profissional;
import br.com.util.JPAUtil;

@Path("/profissional")
public class ProfissionalResource {
	
	private ProfissionalDao dao;
	
	@GET
	@Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
	public Profissional profissionalBy(@PathParam("cpf") String cpf){
		dao = new ProfissionalDao(JPAUtil.getEntityManager());
		Profissional profissional = dao.findByCpf(cpf);
		JPAUtil.closeEntityManagerFactory();
		return profissional;
	}

}
