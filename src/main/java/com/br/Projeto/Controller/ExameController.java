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

import com.br.Projeto.model.Exame;
import com.br.Projeto.repository.ExameRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/exame")
public class ExameController {
	
	private final ExameRepository exameRepository;

	public ExameController(ExameRepository exameRepository) {
		super();
		this.exameRepository = exameRepository;
	}
	
	@GetMapping
	public List<Exame> listarExames()
	{
		return exameRepository.findAll();
	}
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	public Exame salvarExame(@RequestBody Exame Exame)
	{
		return exameRepository.save(Exame);
	}
	
	 @GetMapping("{id}")
	public Exame ListarPorCodigo(@PathVariable Long id)
	{
		return exameRepository.findById(id)
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exame nao encontrado"));
	}
	
	 @DeleteMapping("{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id)
	{
		exameRepository.findById(id)
			.map(exame -> {
				exameRepository.delete(exame);
				return Void.TYPE;
			}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exame nao encontrado"));
	}
	
	 @PutMapping("{id}")
	public void Atualizar(@PathVariable Long id, @RequestBody @Valid Exame ExameAtualizado)
	{
		exameRepository.findById(id).map(exame ->
		{
			 exame.setDescricao(ExameAtualizado.getDescricao());
			 exame.setPreco(ExameAtualizado.getPreco());
			return exameRepository.save(exame);
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exame nao encontrado"));
	}
	

}
