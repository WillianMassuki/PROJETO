package com.br.Projeto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@Column
	private Date data;
	
	@Column
	private Paciente paciente;
	
	@Column
	private String Convenio;
	
	@JoinColumn
	private PostoColeta postoColeta;
	
	@JoinColumn
	private Medico medico;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getConvenio() {
		return Convenio;
	}

	public void setConvenio(String convenio) {
		Convenio = convenio;
	}

	public PostoColeta getPostoColeta() {
		return postoColeta;
	}

	public void setPostoColeta(PostoColeta postoColeta) {
		this.postoColeta = postoColeta;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	
	

}
