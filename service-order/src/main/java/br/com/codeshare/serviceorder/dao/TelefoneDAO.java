package br.com.codeshare.serviceorder.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.codeshare.serviceorder.entidade.Telefone;

@Stateless
public class TelefoneDAO {

	@PersistenceContext
	EntityManager manager;
	
	public void salva(Telefone telefone){
		manager.persist(telefone);
	}
	
}
