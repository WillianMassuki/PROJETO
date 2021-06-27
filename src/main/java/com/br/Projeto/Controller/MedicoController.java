package com.br.Projeto.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.Projeto.model.Medico;
import com.br.Projeto.repository.MedicoRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/medico")
public class MedicoController {
	
	private final MedicoRepository medicoRepository;

	public MedicoController(MedicoRepository medicoRepository) {
		super();
		this.medicoRepository = medicoRepository;
	}
	
	@GetMapping
	public List<Medico> listarMedicos()
	{
		return medicoRepository.findAll();
	}
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	public Medico salvarMedico(@RequestBody Medico Medico)
	{
		return medicoRepository.save(Medico);
	}
	
	 @GetMapping("{id}")
	public Medico ListarPorCodigo(@PathVariable Long id)
	{
		return medicoRepository.findById(id)
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico buscado não encontrado em nossa base"));
	}
	
	 @DeleteMapping("{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id)
	{
		medicoRepository.findById(id)
			.map(contato -> {
				medicoRepository.delete(contato);
				return Void.TYPE;
			}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico buscado não encontrado em nossa base"));
	}
	
	 @PutMapping("{id}")
	public void Atualizar(@PathVariable Long id, @RequestBody @Valid Medico MedicoAtualizado)
	{
		medicoRepository.findById(id).map(medico ->
		{
			medico.setNome(MedicoAtualizado.getNome());
			medico.setEspecialidade(MedicoAtualizado.getEspecialidade());
			 
			return medicoRepository.save(medico);
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico buscado não encontrado em nossa base"));
	}

}
