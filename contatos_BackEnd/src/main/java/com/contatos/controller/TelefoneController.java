package com.contatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.contatos.model.Telefone;
import com.contatos.repository.TelefoneRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@GetMapping("/{id_contato}")
	public ResponseEntity<List<Telefone>> getTelefonesPorContato(@PathVariable Long contatoId) {
        List<Telefone> telefones = telefoneRepository.findByContatoId(contatoId);

        if (telefones.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(telefones);
        }
    }
	
	@PostMapping
	public ResponseEntity<Telefone> post(@Valid @RequestBody Telefone telefone){
		return ResponseEntity.status(HttpStatus.CREATED).body(telefoneRepository.save(telefone));
	}
	
	@PutMapping
	public ResponseEntity<Telefone> put(@Valid @RequestBody Telefone telefone){
		return telefoneRepository.findById(telefone.getId())
				.map(ResponseStatus -> ResponseEntity.status(HttpStatus.OK)
				.body(telefoneRepository.save(telefone)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Telefone> telefone = telefoneRepository.findById(id);
		
		if (telefone.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);	
		}
		telefoneRepository.deleteById(id);
	}
	
}
