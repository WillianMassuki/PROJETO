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

import com.br.Projeto.model.PostoColeta;
import com.br.Projeto.repository.PostoColetaRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/OrdemServicoExame")
public class PostoColetaController {
	
	private final PostoColetaRepository postoColetaRepository;

	public PostoColetaController(PostoColetaRepository postoColetaRepository) {
		super();
		this.postoColetaRepository = postoColetaRepository;
	}
	
	@GetMapping
	public List<PostoColeta> listarPacientes()
	{
		return postoColetaRepository.findAll();
	}
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	public PostoColeta salvarExame(@RequestBody PostoColeta  paciente)
	{
		return postoColetaRepository.save(paciente);
	}
	
	 @GetMapping("{id}")
	public PostoColeta ListarPorCodigo(@PathVariable Long id)
	{
		return postoColetaRepository.findById(id)
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "informações de coleta nao encontrado"));
	}
	
	 @DeleteMapping("{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id)
	{
		postoColetaRepository.findById(id)
			.map(contato -> {
				postoColetaRepository.delete(contato);
				return Void.TYPE;
			}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "informações de coleta nao encontrado"));
	}
	
	 @PutMapping("{id}")
	public void Atualizar(@PathVariable Long id, @RequestBody @Valid PostoColeta PostoColetaAtualizado)
	{
		postoColetaRepository.findById(id).map(postoColeta ->
		{
			 postoColeta.setDescricao(PostoColetaAtualizado.getDescricao());
			 postoColeta.setEndereco(PostoColetaAtualizado.getEndereco());
			
			return postoColetaRepository.save(postoColeta);
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "informações de coleta nao encontrado"));
	}

}
