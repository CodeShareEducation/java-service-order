package br.com.codeshare.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.codeshare.data.PhoneRepository;
import br.com.codeshare.model.Phone;

@Stateless
public class PhoneService {

	@Inject 
	private Logger log;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Event<Phone> phoneEvent;
	
	@Inject
	private PhoneRepository phoneRepository;
	
	public void register(Phone phone) throws Exception{
		log.info("Registering " + phone.getModel());
		em.persist(phone);
		phoneEvent.fire(phone);
	}
	
	public List<Phone> recoverClientPhones(Integer clientId){
		log.info("Recovering phones");
		return phoneRepository.findClientPhone(clientId);
	}
}
