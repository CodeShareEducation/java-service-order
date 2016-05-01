package br.com.codeshare.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.codeshare.builder.ClientBuilder;
import br.com.codeshare.builder.PhoneBuilder;
import br.com.codeshare.builder.ServiceOrderBuild;
import br.com.codeshare.data.AbstractRepository;
import br.com.codeshare.data.ClientRepository;
import br.com.codeshare.data.PhoneRepository;
import br.com.codeshare.data.Repository;
import br.com.codeshare.data.ServiceOrderRepository;
import br.com.codeshare.enums.PhoneState;
import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.exception.BusinessException;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;
import br.com.codeshare.service.ClientService;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.service.ServiceOrderService;
import br.com.codeshare.util.Resources;

@RunWith(Arquillian.class)
public class ServiceOrderServiceTest {

	@Deployment
	public static Archive<?> createTestArchive(){
		return ShrinkWrap.create(WebArchive.class,"test.war")
				.addClasses(ServiceOrder.class,ServiceOrderService.class,ServiceOrderRepository.class,Resources.class,Client.class,Phone.class,
						AbstractRepository.class,Repository.class,PhoneState.class,ServiceOrderType.class,ServiceOrderState.class,ClientBuilder.class,
						PhoneBuilder.class,ServiceOrderBuild.class,ClientService.class,BusinessException.class,ClientRepository.class,PhoneService.class,
						PhoneRepository.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}
	
	@Inject
	ServiceOrderService service;
	
	@Inject
	ClientService clientService;
	
	@Inject
	PhoneService phoneService;
	
	@Inject
	Logger log;
	
	@Test
	public void testRegister() throws Exception{
		Phone phone = new PhoneBuilder()
				.withBrand("Samsung")
				.withModel("Galaxy S6")
				.buid();
		
		Client client = new ClientBuilder()
				.withName("John Mc.Queide")
				.withAdress("Quadra 101 Conjunto 07 Casa 07")
				.withHomePhone("(61) 1234-9812")
				.withPhone(Arrays.asList(phone))
				.build();
		
		ServiceOrder serviceOrder = new ServiceOrderBuild()
				.withReportedProblem("Don't work")
				.withValue(new BigDecimal(500))
				.withClient(client)
				.withPhone(phone)
				.build();
		
		clientService.save(client);
		
		service.register(serviceOrder);
		
		Assert.assertNotNull(serviceOrder.getId());
		log.info(serviceOrder.getClient().getName() + "\'s service order was persisted with id " + serviceOrder.getId());
	}
	
}
