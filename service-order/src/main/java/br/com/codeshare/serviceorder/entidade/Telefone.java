package br.com.codeshare.serviceorder.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import br.com.codeshare.serviceorder.dto.EstadoTelefone;

@Getter
@Setter
@Entity
@Table(name="telefone")
public class Telefone {

	@SequenceGenerator(name="SEQ_TEL",sequenceName="SEQ_TEL",initialValue=1,allocationSize=1)
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_TEL")
	@Column(name="telefone_id")
	private int id;
	private String marca;
	private String modelo;
	@Column(name="est_tel")
	@Enumerated(EnumType.ORDINAL)
	private EstadoTelefone estado;
	private String esn;
	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy="telefone")
	private List<OrdemServico> os;
}
