package com.br.Projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PostoColeta {
	
	@Column
	private String descricao;
	
	@Column
	private String endereco;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

}
