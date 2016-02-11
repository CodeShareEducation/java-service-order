package br.com.wc.ordemservico.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoDTO {

	private Long numeroOS;
	private String problemaRelatado;
	private String problemaEncontrado;
	private String servicoExecutado;
	private TipoOrdemServico tipoOrdemServico;
	private Date dataAprovacao;
	private Date dataPrevistaRetirada;
	private ClienteDTO cliente;
}
