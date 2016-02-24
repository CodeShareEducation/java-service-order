package br.com.codeshare.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.codeshare.data.ServiceOrderRepository;
import br.com.codeshare.model.ServiceOrder;

@Stateless
public class ServiceOrderService {

	@Inject
	ServiceOrderRepository soRepository;
	
	@Inject
	private Event<ServiceOrder> soEventSrc;
	
    public void register(ServiceOrder serviceOrder) throws Exception {
    	soRepository.insert(serviceOrder);
    	soEventSrc.fire(serviceOrder);
    }

    public List<ServiceOrder> findAll(){
    	return soRepository.findAll();
    }
    
    public ServiceOrder find(Long id){
    	return soRepository.findById(id);
    }
    
    public List<ServiceOrder> findClientByName(String name){
    	return soRepository.findClientByName(name);
    }
}
