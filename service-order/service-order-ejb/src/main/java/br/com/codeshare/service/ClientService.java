package br.com.codeshare.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.codeshare.data.ClientRepository;
import br.com.codeshare.model.Client;

@Stateless
public class ClientService{

	@Inject
	private ClientRepository clientRepository;
	@Inject
	private Event<Client> clientEventSrc;
	
	public void save(Client client) throws Exception{
		clientRepository.insert(client);
		clientEventSrc.fire(client);
	}
	
}
