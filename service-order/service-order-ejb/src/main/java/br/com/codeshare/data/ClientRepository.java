package br.com.codeshare.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.Client;

@RequestScoped
public class ClientRepository extends AbstractRepository<Client>{

	public List<Client> findClientByName(String name) {
		TypedQuery<Client> query = em.createQuery("from Client c where lower (c.name) like :name", Client.class);
		query.setParameter("name", "%"+name.toLowerCase()+"%");
		
		return query.getResultList();
	}
	
}
