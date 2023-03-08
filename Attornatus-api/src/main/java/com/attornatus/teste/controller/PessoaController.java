package com.attornatus.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.teste.PessoaService;
import com.attornatus.teste.model.Pessoa;
import com.attornatus.teste.repository.PessoaRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa criar(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable(value = "id") Long id, @RequestBody Pessoa pessoa) {
		Optional<Pessoa> pessoaO = pessoaRepository.findById(id);
		if (pessoaO.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		pessoa.setId(pessoaO.get().getId());
		return new ResponseEntity<Pessoa>(pessoaRepository.save(pessoa), HttpStatus.OK);
	}

}
