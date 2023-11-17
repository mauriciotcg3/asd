package com.contatos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.contatos.model.Contato;
import com.contatos.model.ContatoTelefoneDTO;
import com.contatos.repository.ContatoRepository;
import com.contatos.repository.TelefoneRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contato")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContatosController {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@GetMapping
	public ResponseEntity<List <ContatoTelefoneDTO>> getAll(){
		List <ContatoTelefoneDTO> listaContatoTelefoneDTO = new ArrayList<>();
		List <Contato> contatos = contatoRepository.findAll();
		for (Contato contato : contatos) {
			List <String> numeros = telefoneRepository.findNumerosById(contato.getId());
			listaContatoTelefoneDTO.add(new ContatoTelefoneDTO(contato.getNome(), contato.getIdade(), numeros));
		}
		return ResponseEntity.ok(listaContatoTelefoneDTO);
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List <ContatoTelefoneDTO>> getByNome(@PathVariable String nome){
		List <ContatoTelefoneDTO> listaContatoTelefoneDTO = new ArrayList<>();
		List <Contato> contatos = contatoRepository.findAllByNomeContainingIgnoreCase(nome);
		for (Contato contato : contatos) {
			List <String> numeros = telefoneRepository.findNumerosById(contato.getId());
			listaContatoTelefoneDTO.add(new ContatoTelefoneDTO(contato.getNome(), contato.getIdade(), numeros));
		}
		return ResponseEntity.ok(listaContatoTelefoneDTO);
	}
	
	@PostMapping
	public ResponseEntity<Contato> post(@Valid @RequestBody Contato contato){
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoRepository.save(contato));
	}
	
	@PutMapping
	public ResponseEntity<Contato> put(@Valid @RequestBody Contato contato){
		return contatoRepository.findById(contato.getId())
				.map(ResponseStatus -> ResponseEntity.status(HttpStatus.OK)
				.body(contatoRepository.save(contato)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		
		if (contato.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);	
		}
		contatoRepository.deleteById(id);
	}
	
}
