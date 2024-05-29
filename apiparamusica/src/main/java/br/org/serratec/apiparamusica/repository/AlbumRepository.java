package br.org.serratec.apiparamusica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.apiparamusica.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistaNome(String nomeArtista);
    List<Album> findByNome(String nome);

}
