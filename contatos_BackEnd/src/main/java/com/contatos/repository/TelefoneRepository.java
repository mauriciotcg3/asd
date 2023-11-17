package com.contatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contatos.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
	@Query(value = "SELECT numero FROM tb_telefone WHERE id_contato = :id", nativeQuery = true)
	public List<String> findNumerosById(@Param("id") Long id);

	public List<Telefone> findByContatoId(Long contatoId);
}
