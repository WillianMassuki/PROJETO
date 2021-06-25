package com.br.Projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Exame {
	
	@Column
	private String descricao;
	
	@Column
	private Double preco;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	

}
