package br.org.serratec.apiparamusica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.apiparamusica.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
	    List<Musica> findByGenero(String genero);
	    List<Musica> findByTitulo(String titulo);
	    List<Musica> findByAlbumNome(String nomeAlbum);

}
