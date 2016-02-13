package br.com.codeshare.serviceorder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneDTO {

	private int id;
	private String marca;
	private String modelo;
	private EstadoTelefone estado;
	private String esn;
	private ClienteDTO cliente;
}
