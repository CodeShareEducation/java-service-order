package br.com.codeshare.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.ServiceOrder;

@RequestScoped
public class ServiceOrderRepository extends AbstractRepository<ServiceOrder>{

	public List<ServiceOrder> findClientByName(String nameFilter) {
		TypedQuery<ServiceOrder> query = 
				em.createQuery("from OrdemServico os join fetch os.cliente join fetch os.telefone where lower (os.cliente.nome) like :nome",ServiceOrder.class);
		query.setParameter("nome", "%"+nameFilter.toLowerCase()+"%");
		return query.getResultList();
	}
}
