package br.com.codeshare.serviceorder.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="parametro")
public class Parametro {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PMT")
	@SequenceGenerator(name="SEQ_PMT",sequenceName="SEQ_PMT",initialValue=1,allocationSize=1)
	private Integer id;
	private String nomeLoja;
	private String descrição;
	private String endereco;
	private String telefone;
}
