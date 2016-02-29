package br.com.codeshare.util;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	private String localeCode;
	private Locale locale;

	@PostConstruct
	private void init() {
		locale = facesContext.getExternalContext().getRequestLocale();
	}

	public String getLocaleCode() {
		return localeCode;
	}
	
	public String getLanguage()
	{
		return locale.getLanguage();
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	// value change event listener
	public void countryLocaleCodeChanged() {
		locale = new Locale(localeCode);
		facesContext.getViewRoot().setLocale(locale);
	}
}
