package br.com.wc.ordemservico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoOrdemServico {

	ORCAMENTO(0,"Orçamento"),
	SERVICO(1,"Serviço");
	
	private Integer codigo;
	private String label;
	
}
