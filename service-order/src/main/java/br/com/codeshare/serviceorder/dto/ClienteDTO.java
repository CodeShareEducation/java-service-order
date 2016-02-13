package br.com.codeshare.serviceorder.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

	private int id;
	private String nome;
	private String endereco;
	private String telefoneResidencial;
	private String telefoneComercial;
	private List<OrdemServicoDTO> ordemServicos;
	private List<TelefoneDTO> telefones;
}
