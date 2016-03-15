package br.com.codeshare.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.service.ClientService;

@Named
@ConversationScoped
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ClientService clientService;

	private Client newClient;

	@Inject
	private PhoneController phoneController;
	
	@Inject
	private Conversation conversation;
	
	private String filterName;

	private List<Client> listClients;

	private Client clientSelected;

	@Produces
	@Named
	public Client getNewClient() {
		return newClient;
	}

	@PostConstruct
	public void initNewClient() {
		newClient = new Client();
		newClient.setTelefones(new ArrayList<Phone>());
		listClients = clientService.findAll();
	}
	
	public String save() throws Exception {
		try {
			clientService.save(newClient);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!","Registration successful"));
			initNewClient();
			if(!conversation.isTransient()){
				conversation.end();
			}
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	public String update(Client client) throws Exception{
		try {
			clientService.update(client);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
			initNewClient();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
		}
		return "clients";
	}

	private String getRootErrorMessage(Exception e) {
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			return errorMessage;
		}

		Throwable t = e;
		while (t != null) {
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		return errorMessage;
	}

	public void addClientPhone() {
		if(conversation.isTransient()){
			conversation.begin();
		}
		
		phoneController.getNewPhone().setClient(newClient);
		if (newClient.getPhones() == null) {
			newClient.setTelefones(new ArrayList<Phone>());
		}
		newClient.getPhones().add(phoneController.getNewPhone());
		phoneController.initNewPhone();
	}
	
	public void addClientPhoneOnUpdate() {
		if(conversation.isTransient()){
			conversation.begin();
		}
		
		phoneController.getNewPhone().setClient(clientSelected);
		if (clientSelected.getPhones() == null) {
			clientSelected.setTelefones(new ArrayList<Phone>());
		}
		clientSelected.getPhones().add(phoneController.getNewPhone());
		phoneController.initNewPhone();
	}
	
	public void searchByName() {
		listClients = null;
		if(filterName == null){
			listClients = clientService.findAll();
		}
		listClients = clientService.findByName(filterName);
	}
	
	public String edit(Client client) {
		this.clientSelected = client;
		facesContext.getCurrentInstance().getExternalContext().getSessionMap().put("client", client);
		return "update_client";
	}
	
	public Client getClientSelected() {
		return (Client) facesContext.getCurrentInstance().getExternalContext().getSessionMap().get("client");
	}
	
	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public List<Client> getListClients() {
		return listClients;
	}
	
}
