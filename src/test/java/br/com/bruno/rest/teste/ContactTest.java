package br.com.bruno.rest.teste;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.*;
import org.junit.Test;

public class ContactTest {

	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/restws");
		String conteudo = target.path("/contacts").request().get(String.class);
		assertTrue(conteudo.contains("<name>Marcia"));
		
		
	}
}
