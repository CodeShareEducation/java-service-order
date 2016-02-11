package br.com.wc.ordemservico.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.wc.ordemservico.entidade.Telefone;

@Stateless
public class TelefoneDAO {

	@PersistenceContext
	EntityManager manager;
	
	public void salva(Telefone telefone){
		manager.persist(telefone);
	}
	
}
