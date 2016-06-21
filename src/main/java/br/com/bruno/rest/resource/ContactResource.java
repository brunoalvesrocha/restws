package br.com.bruno.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.bruno.rest.dao.ContactDAO;
import br.com.bruno.rest.dao.IContactDAO;
import br.com.bruno.rest.domain.Contact;

@Path("/contacts")
public class ContactResource {

	private static final Logger LOGGER = Logger.getLogger(ContactResource.class);

	private IContactDAO<Contact, Long> dao;

	public ContactResource() {
		this.dao = new ContactDAO();
	}

	@POST
	@Consumes({MediaType.APPLICATION_XML})
	public Response save(Contact contact) {
		try {
		
			dao.save(contact);	
			
			return Response
					.status(200)
					.entity("Registro inserido: " + contact.toString())
					.build();	
			
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes({MediaType.APPLICATION_XML})
	public Response update(Contact contact) {
		try {
			
			dao.update(contact);
			
			return Response
					.status(200)
					.entity("Registro editado.")
					.build();	
			
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML})
	public Response delete(@PathParam("id") Long id) {
		try {
			
			dao.delete(id);
			
			return Response
					.status(200)
					.entity("Registro removido.")
					.build();
			
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML})
	public Contact findById(@PathParam("id") Long id) {
		try {

			return dao.findById(id);
			
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Contact> findAll() {
		try {
			
			return dao.findAll(); 
			
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findByName(@PathParam("name") String name) {
		LOGGER.info("NAME : " + name);
		try {
			
			List<Contact> contacts = dao.findByName(name);
			
			GenericEntity<List<Contact>> entities = new GenericEntity<List<Contact>>(contacts) {};
			
			return Response
					.ok(entities)
					.build();
			
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}
}
