package br.com.codeshare.controller;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codeshare.enums.PhoneState;
import br.com.codeshare.model.Phone;
import br.com.codeshare.service.PhoneService;
import br.com.codeshare.util.Resources;

@Model
public class PhoneController {

	
	@Inject
	private FacesContext facesContext;
	@Inject
	private Locale locale;
	@Inject
	private PhoneService phoneService;
	
	private Phone newPhone;
	
	@Produces
	@Named
	public Phone getNewPhone(){
		return newPhone;
	}
	
	public void save() throws Exception{
		try {
			phoneService.register(newPhone);
			facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, Resources.getMessage("register",locale), Resources.getMessage("sucess_register",locale)));
			initNewPhone();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, Resources.getMessage("unsuccessful",locale));
            facesContext.addMessage(null, m);
		}
	}
	
	@PostConstruct
	public void initNewPhone(){
		newPhone = new Phone();
	}
	
	private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
	
	@Produces
	@Named
	public PhoneState[] getPhoneStates(){
		return PhoneState.values();
	}
	
}
