package br.com.wc.ordemservico.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.wc.ordemservico.entidade.Cliente;

@Stateless
public class ClienteDAO {

	@PersistenceContext
	EntityManager manager;
	
	public void salva(Cliente cliente){
		manager.persist(cliente);
	}
}
