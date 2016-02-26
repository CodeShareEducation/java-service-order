package br.com.codeshare.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.Phone;

@RequestScoped
public class PhoneRepository extends AbstractRepository<Phone>{
	
	public List<Phone> findClientPhone(Integer clientId) {
		TypedQuery<Phone> query = 
				em.createQuery("from Phone phone where phone.client.id = :clientId",Phone.class);
		query.setParameter("clientId", clientId);
		return query.getResultList();
	}

}
