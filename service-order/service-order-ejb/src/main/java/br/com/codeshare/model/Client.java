package br.com.codeshare.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="SEQ_CLIENT",sequenceName="SEQ_CLIENT",initialValue=1,allocationSize=1)
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_CLIENT")
	@Column(name="cliente_id")
	private Long id;
	private String name;
	private String adress;
	@Column(name="home_phone")
	private String homePhone;
	@Column(name="business_phone")
	private String bisenessPhone;
	@OneToMany(mappedBy="client")
	private List<ServiceOrder> ordemServicos;
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Phone> phones;
	
	public Client() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getBisenessPhone() {
		return bisenessPhone;
	}
	public void setBisenessPhone(String bisenessPhone) {
		this.bisenessPhone = bisenessPhone;
	}
	public List<ServiceOrder> getOrdemServicos() {
		return ordemServicos;
	}
	public void setOrdemServicos(List<ServiceOrder> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setTelefones(List<Phone> phones) {
		this.phones = phones;
	}
}
