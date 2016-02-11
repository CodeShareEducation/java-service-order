package br.com.wc.ordemservico.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cliente")
public class Cliente {

	@SequenceGenerator(name="SEQ_CLIENTE",sequenceName="SEQ_CLIENTE",initialValue=1,allocationSize=1)
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_CLIENTE")
	@Column(name="cliente_id")
	private int id;
	private String nome;
	private String endereco;
	@Column(name="tel_residencial")
	private String telefoneResidencial;
	@Column(name="tel_comercial")
	private String telefoneComercial;
	@OneToMany(mappedBy="cliente")
	private List<OrdemServico> ordemServicos;
	@OneToMany(mappedBy="cliente")
	private List<Telefone> telefones;
	
}
