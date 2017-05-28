package br.com.main;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import br.com.entity.Profissional;

public class ProfissionalTeste {
	public static void main(String[] args) {
		String url = "http://localhost:8080/webservice/profissional/";
		String name = "usuarioteste";
		String password = "123456";
		String authString = name + ":" + password;
		String authStringEnc = new String(Base64.encode(authString.getBytes()));
		System.out.println("Base 64 encoded auth String: " + authStringEnc);
		Client restClient = ClientBuilder.newClient();
		WebTarget webResource = restClient.target(url+"22222222222");
		Response resp = webResource.request("application/json")
							.header("Authorization", "Basic " + authStringEnc)
							.get(Response.class);
		if(resp.getStatus()!=200) {
			System.out.println("Sem conexão com servidor");
			
		}else{
			Profissional profissional = (Profissional) resp.readEntity(Object.class);
			System.out.println(profissional);
		}
	}
}
