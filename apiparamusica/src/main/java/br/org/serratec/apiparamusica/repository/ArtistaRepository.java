package br.org.serratec.apiparamusica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.apiparamusica.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
	Optional<Artista> findByNome(String nome);
	List<Artista> findByGenero(String genero);
}

