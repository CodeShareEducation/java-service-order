package br.com.wc.ordemservico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoTelefone {

	SEM_CHIP(0,"Sem chip"),
	CARTAO_MEMORIA(1,"Cartão de memória"),
	TAMPA(2,"Tampa"),
	BATERIA(3,"Bateria");
	
	private Integer codigo;
	private String label;
	
}
