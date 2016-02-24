package br.com.codeshare.data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AbstractRepository<T extends Serializable> implements
		Repository<T> {

	@Inject
	private EntityManager em;
	@Inject
	private Logger log;
	@Inject
	private Event<T> eventSrc;

	@SuppressWarnings("unchecked")
	private Class<T> getType() {
		ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) (type).getActualTypeArguments()[0];
	}

	@Override
	public void insert(T bean) {
		log.info("Gravando " + getType());
		em.persist(bean);
		eventSrc.fire(bean);
	}

	@Override
	public void update(T bean) {
		bean = em.merge(bean);
		eventSrc.fire(bean);
	}

	@Override
	public void delete(T bean) {
		em.remove(bean);
		eventSrc.fire(bean);
	}

	@Override
	public T findById(Long id) {
		return em.find(getType(), id);
	}

	@Override
	public List<T> findAllOrderedByName() {
		log.info("Recovering data ...");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(getType());
		Root<T> root = criteria.from(getType());
		criteria.select(root).orderBy(cb.asc(root.get("nome")));
		return em.createQuery(criteria).getResultList();
	}

}
