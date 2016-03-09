package br.com.codeshare.enums;

public enum ServiceOrderState {

	OPEN(0,"Aberto"),
	WAITING_APPROVAL(1,"Aguardando aprovação"),
	APPROVED(2,"Aprovado"),
	COMPLETED(3,"Finalizada");
	
	private Integer id;
	private String label;

	private ServiceOrderState(Integer id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
