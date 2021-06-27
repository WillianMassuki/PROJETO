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

import com.br.Projeto.model.Exame;
import com.br.Projeto.model.OrdemServico;
import com.br.Projeto.model.OrdemServicoExame;
import com.br.Projeto.repository.OrdemServicoRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/OrdemServico")
public class OrdemServicoController {
	
	private final OrdemServicoRepository ordemServicoRepository;

	public OrdemServicoController(OrdemServicoRepository ordemServicoRepository) {
		super();
		this.ordemServicoRepository = ordemServicoRepository;
	}
	
	@GetMapping
	public List<OrdemServico> listarOrdemServico()
	{
		return ordemServicoRepository.findAll();
	}
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	public OrdemServico  salvarExame(@RequestBody OrdemServico  ordemServicoExame)
	{
		return ordemServicoRepository.save(ordemServicoExame);
	}
	
	 @GetMapping("{id}")
	public OrdemServico ListarPorCodigo(@PathVariable Long id)
	{
		return ordemServicoRepository.findById(id)
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de serviço nao consta em nossa base"));
	}
	
	 @DeleteMapping("{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id)
	{
		ordemServicoRepository.findById(id)
			.map(contato -> {
				ordemServicoRepository.delete(contato);
				return Void.TYPE;
			}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de serviço nao consta em nossa base"));
	}
	
	 @PutMapping("{id}")
	public void Atualizar(@PathVariable Long id, @RequestBody @Valid OrdemServico OrdemServicoAtualizado)
	{
		ordemServicoRepository.findById(id).map(ordemServico ->
		{
			ordemServico.setConvenio(OrdemServicoAtualizado.getConvenio());
			ordemServico.setData(OrdemServicoAtualizado.getData());
			ordemServico.setMedico(OrdemServicoAtualizado.getMedico());
			ordemServico.setPaciente(OrdemServicoAtualizado.getPaciente());
			ordemServico.setPostoColeta(OrdemServicoAtualizado.getPostoColeta());
			
			return ordemServicoRepository.save(ordemServico);
			
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de serviço nao consta em nossa base"));
	}


}
