package br.com.wc.ordemservico.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import br.com.wc.ordemservico.dto.EstadoOrdemServico;
import br.com.wc.ordemservico.dto.TipoOrdemServico;

@NamedQueries(
		{@NamedQuery(name=OrdemServico.BUSCAR_TODAS,query="select os from OrdemServico os join fetch os.cliente join fetch os.telefone"),
		@NamedQuery(name=OrdemServico.BUSCAR_POR_CLIENTE,query="select os from OrdemServico os join fetch os.cliente join fetch os.telefone where os.cliente.nome like :nome")}
		)
@Getter
@Setter
@Entity
@Table(name="os")
public class OrdemServico {

	public static final String BUSCAR_TODAS = "OrdemServico.listarTodas";
	public static final String BUSCAR_POR_CLIENTE = "OrdemServico.porNomeCliente";
	
	@SequenceGenerator(name="SEQ_OS",sequenceName="SEQ_OS",initialValue=1,allocationSize=1)
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_OS")
	@Column(name="num_os")
	private Long numeroOS;
	
	@Column(name="prob_relat")
	private String problemaRelatado;
	
	@Column(name="prob_encont")
	private String problemaEncontrado;
	
	@Column(name="serv_exec")
	private String servicoExecutado;
	
	@Column(name="tip_os")
	@Enumerated(EnumType.ORDINAL)
	private TipoOrdemServico tipoOrdemServico;
	
	@Column(name="estado_os")
	@Enumerated(EnumType.ORDINAL)
	private EstadoOrdemServico estadoOrdemServico;
	
	@Column(name="data_os")
	@Temporal(TemporalType.DATE)
	private Date dataOS;
	
	@Column(name="dat_aprov")
	@Temporal(TemporalType.DATE)
	private Date dataAprovacao;
	
	@Column(name="dat_prev_ret")
	@Temporal(TemporalType.DATE)
	private Date dataPrevistaRetirada;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Telefone telefone;
}
