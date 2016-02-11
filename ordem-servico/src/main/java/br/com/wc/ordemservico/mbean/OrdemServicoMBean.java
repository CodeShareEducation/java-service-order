package br.com.wc.ordemservico.mbean;

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
import br.com.wc.ordemservico.dao.ClienteDAO;
import br.com.wc.ordemservico.dao.OrdemServicoDAO;
import br.com.wc.ordemservico.dao.TelefoneDAO;
import br.com.wc.ordemservico.dto.EstadoOrdemServico;
import br.com.wc.ordemservico.dto.EstadoTelefone;
import br.com.wc.ordemservico.dto.TipoOrdemServico;
import br.com.wc.ordemservico.entidade.Cliente;
import br.com.wc.ordemservico.entidade.OrdemServico;
import br.com.wc.ordemservico.entidade.Telefone;

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
	
	@PostConstruct
	public void constroiObjetos(){
		cliente = new Cliente();
		telefone = new Telefone();
		os = new OrdemServico();
		estados = EstadoTelefone.values();
		tipoOs = TipoOrdemServico.values();
		estadoOs = EstadoOrdemServico.values();
		ordensServicos = daoOS.listarTotas();
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
	
	public void buscaPorNome(ValueChangeEvent event) throws Exception{
		ordensServicos = null;
		if((String)event.getNewValue() == null){
			ordensServicos = daoOS.listarTotas();
		}
		ordensServicos = daoOS.listarPorNome((String)event.getNewValue());
		
	}
}
