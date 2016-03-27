package br.com.codeshare.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.codeshare.data.ClientRepository;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;

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
	
	public Client findById(Long id)
	{
		return clientRepository.findById(id);
	}
	
	public List<Client> findAll(){
		return clientRepository.findAllOrderedByName();
	}
	
	public List<Client> findByName(String name) {
		return clientRepository.findClientByName(name);
	}

	public void update(Client client) throws Exception {
		clientRepository.update(client);
		clientEventSrc.fire(client);
	}
	
	public void removePhoneCliente(Client client, Phone phone){
		clientRepository.removePhoneClient(client, phone);
	}
	
}
