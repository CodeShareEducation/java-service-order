package br.com.codeshare.serviceorder.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.codeshare.serviceorder.entidade.OrdemServico;

@Stateless
public class OrdemServicoDAO {

	@PersistenceContext
	EntityManager manager;
	
	public void salva(OrdemServico os){
		manager.persist(os);
	}
	
	public OrdemServico find(long numOS){
		return manager.find(OrdemServico.class, numOS);
	}

	public List<OrdemServico> listarTodas() {
		TypedQuery<OrdemServico> query = manager.createNamedQuery(OrdemServico.BUSCAR_TODAS, OrdemServico.class);
		return query.getResultList();
	}
	
	public List<OrdemServico> listarPorNome(String nome) {
		TypedQuery<OrdemServico> query = manager.createNamedQuery(OrdemServico.BUSCAR_POR_CLIENTE, OrdemServico.class);
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}
	
	public void altera(OrdemServico os){
		OrdemServico merge = manager.merge(os);
		manager.persist(merge);
	}
	
}
