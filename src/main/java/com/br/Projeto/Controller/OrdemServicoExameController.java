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

import com.br.Projeto.model.OrdemServicoExame;
import com.br.Projeto.repository.OrdemServicoExameRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/OrdemServicoExame")
public class OrdemServicoExameController {
	
	private final OrdemServicoExameRepository  ordemServicoExameRepository;
	
	public OrdemServicoExameController(OrdemServicoExameRepository ordemServicoExameRepository) {
		super();
		this.ordemServicoExameRepository = ordemServicoExameRepository;
	}

	@GetMapping
	public List<OrdemServicoExame> listarServicoExame()
	{
		return ordemServicoExameRepository.findAll();
	}
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoExame salvarExame(@RequestBody OrdemServicoExame ordemServicoExame)
	{
		return ordemServicoExameRepository.save(ordemServicoExame);
	}
	
	 @GetMapping("{id}")
	public OrdemServicoExame ListarPorCodigo(@PathVariable Long id)
	{
		return ordemServicoExameRepository.findById(id)
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de serviço do exame nao consta em nossa base"));
	}
	
	 @DeleteMapping("{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id)
	{
		ordemServicoExameRepository.findById(id)
			.map(contato -> {
				ordemServicoExameRepository.delete(contato);
				return Void.TYPE;
			}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de serviço do exame nao consta em nossa base"));
	}
	
	 @PutMapping("{id}")
	public void Atualizar(@PathVariable Long id, @RequestBody @Valid OrdemServicoExame ordemServicoExameAtualizado)
	{
		ordemServicoExameRepository.findById(id).map(ordemservicoExame ->
		{ 
			ordemservicoExame.setExame(ordemServicoExameAtualizado.getExame());
			ordemservicoExame.setOrdemServico(ordemServicoExameAtualizado.getOrdemServico());
			ordemservicoExame.setPreco(ordemServicoExameAtualizado.getPreco());
			return ordemServicoExameRepository.save(ordemservicoExame);
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de serviço do exame nao consta em nossa base"));
	}

}
