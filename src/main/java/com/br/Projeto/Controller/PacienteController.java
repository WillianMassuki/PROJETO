package com.br.Projeto.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.Projeto.model.Paciente;
import com.br.Projeto.repository.PacienteRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/OrdemServicoExame")
public class PacienteController {
	
	private final PacienteRepository pacienteRepository;

	public PacienteController(PacienteRepository pacienteRepository) {
		super();
		this.pacienteRepository = pacienteRepository;
	}
	
	@GetMapping
	public List<Paciente> listarPacientes()
	{
		return pacienteRepository.findAll();
	}
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	public Paciente salvarExame(@RequestBody Paciente  paciente)
	{
		return pacienteRepository.save(paciente);
	}
	
	 @GetMapping("{id}")
	public Paciente ListarPorCodigo(@PathVariable Long id)
	{
		return pacienteRepository.findById(id)
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "paciente não encontrado"));
	}
	
	 @DeleteMapping("{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id)
	{
		pacienteRepository.findById(id)
			.map(contato -> {
				pacienteRepository.delete(contato);
				return Void.TYPE;
			}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "paciente não encontrado"));
	}
	
	 @PutMapping("{id}")
	public void Atualizar(@PathVariable Long id, @RequestBody @Valid Paciente PacienteAtualizado)
	{
		pacienteRepository.findById(id).map(paciente ->
		{
			paciente.setDataNascimento(PacienteAtualizado.getDataNascimento());
			paciente.setEndereco(PacienteAtualizado.getEndereco());
			paciente.setNome(PacienteAtualizado.getNome());
			paciente.setSexo(PacienteAtualizado.getSexo());
			
			return pacienteRepository.save(paciente);
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "paciente não encontrado"));
	}


}
