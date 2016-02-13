package br.com.codeshare.serviceorder.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.codeshare.serviceorder.entidade.Cliente;

@Stateless
public class ClienteDAO {

	@PersistenceContext
	EntityManager manager;
	
	public void salva(Cliente cliente){
		manager.persist(cliente);
	}
}
