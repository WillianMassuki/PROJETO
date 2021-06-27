package com.br.Projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.Projeto.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
