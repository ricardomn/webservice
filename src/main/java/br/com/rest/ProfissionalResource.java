package br.com.rest;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.util.Base64;

import br.com.dao.ProfissionalDao;
import br.com.entity.Profissional;
import br.com.util.JPAUtil;

@Path("/profissional")
public class ProfissionalResource {
	
	private ProfissionalDao dao;
	
	@GET
	@Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
	public Object profissionalBy(@PathParam("cpf") String cpf, @HeaderParam("authorization") String authString){
		if(!isUserAuthenticaded(authString))
			return "{\"error\": \"Usuário não autentidado!\"}";
		
		dao = new ProfissionalDao(JPAUtil.getEntityManager());
		Profissional profissional = dao.findByCpf(cpf);
		JPAUtil.closeEntityManagerFactory();
		
		
		return profissional;
	}

	private boolean isUserAuthenticaded(String authString) {
		
		if(authString == null)
			return false;
		
		String decodedAuth = "";
		String[] authParts = authString.split("\\s+");
		String authInfor = authParts[1];
		byte[] bytes = null;
		try {
			bytes = Base64.decode(authInfor.getBytes());
		} catch (Exception e) {
			return false;
		}
		decodedAuth = new  String(bytes);
		System.out.println(decodedAuth);
		return true;
	}
	
	
}
