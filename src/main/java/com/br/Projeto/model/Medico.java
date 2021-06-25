package com.br.Projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Medico {
	
	@Column
	private String nome;
	
	@Column
	private String Especialidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return Especialidade;
	}

	public void setEspecialidade(String especialidade) {
		Especialidade = especialidade;
	}
	
	

}
