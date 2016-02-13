package br.com.codeshare.serviceorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoOrdemServico {

	ABERTA(0,"Aberto"),
	AGUARDANDO_APROVACAO(1,"Aguardando aprovação"),
	APROVADO(2,"Aprovado"),
	FINALIZADA(3,"Finalizada");
	
	private Integer codigo;
	private String label;
}
