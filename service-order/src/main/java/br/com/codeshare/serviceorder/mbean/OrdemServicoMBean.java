package br.com.codeshare.serviceorder.mbean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import br.com.codeshare.serviceorder.dao.ClienteDAO;
import br.com.codeshare.serviceorder.dao.OrdemServicoDAO;
import br.com.codeshare.serviceorder.dao.TelefoneDAO;
import br.com.codeshare.serviceorder.dto.EstadoOrdemServico;
import br.com.codeshare.serviceorder.dto.EstadoTelefone;
import br.com.codeshare.serviceorder.dto.TipoOrdemServico;
import br.com.codeshare.serviceorder.entidade.Cliente;
import br.com.codeshare.serviceorder.entidade.OrdemServico;
import br.com.codeshare.serviceorder.entidade.Telefone;

@ManagedBean
@SessionScoped
public class OrdemServicoMBean {

	@Getter
	private Cliente cliente;
	@Getter
	private Telefone telefone;
	@Getter
	private OrdemServico os;
	@Getter
	private OrdemServico osSelecionada;
	@Getter
	private EstadoTelefone[] estados;
	@Getter
	private TipoOrdemServico[] tipoOs;
	@Getter
	private EstadoOrdemServico[] estadoOs;
	private List<OrdemServico> ordensServicos;
	@Inject
	ClienteDAO daoCliente;
	@Inject
	TelefoneDAO daoTelefone;
	@Inject
	OrdemServicoDAO daoOS;
	@Getter
	@Setter
	private String filtroNome;
	
	@PostConstruct
	public void constroiObjetos(){
		cliente = new Cliente();
		telefone = new Telefone();
		os = new OrdemServico();
		estados = EstadoTelefone.values();
		tipoOs = TipoOrdemServico.values();
		estadoOs = EstadoOrdemServico.values();
		ordensServicos = daoOS.listarTodas();
	}
	
	public String salva(){
		daoCliente.salva(cliente);
		telefone.setCliente(cliente);
		daoTelefone.salva(telefone);
		os.setCliente(cliente);
		os.setTelefone(telefone);
		os.setDataOS(new Date());
		daoOS.salva(os);
		
		constroiObjetos();
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Adicionado com sucesso!","")); 
		return "nova_ordem_servico";
	}
	
	public EstadoTelefone obtemEstadoTelefone(int valor){
		for (EstadoTelefone estado : EstadoTelefone.values()) {
			if(estado.getCodigo() == valor){
				return estado;
			}
		}
		return null;
	}
	
	
	public String find(){
		Long numeroOS = os.getNumeroOS();
		osSelecionada = daoOS.find(numeroOS);
		return "detalha_os";
	}
	
	public List<OrdemServico> getOrdensServicos() {
		return ordensServicos;
	}
	
	public String detalhar(OrdemServico os){
		this.osSelecionada = os;
		return "detalha_os";
	}
	
	public String alterar(OrdemServico os){
		this.osSelecionada = os;
		return "alterar_os";
	}
	
	public String altera(OrdemServico osSelecionada){
		if(osSelecionada.getEstadoOrdemServico() == EstadoOrdemServico.APROVADO){
			osSelecionada.setDataAprovacao(new Date());
		}
		daoOS.altera(osSelecionada);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Alterado com sucesso!","")); 
		return "consultar_os";
	}
	
	public void buscaPorNome(){
		ordensServicos = null;
		if(filtroNome == null){
			ordensServicos = daoOS.listarTodas();
		}
		ordensServicos = daoOS.listarPorNome(filtroNome);
		
	}
}
