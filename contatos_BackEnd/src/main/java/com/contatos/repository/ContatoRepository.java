package com.contatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contatos.model.Contato;
import com.contatos.model.ContatoTelefoneDTO;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

	public List<Contato> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	
	@Query("SELECT new com.contatos.model.ContatoTelefoneDTO("
			+ "nome, "
			+ "idade) FROM Contato")
	public List<ContatoTelefoneDTO> findAllContato();

}
