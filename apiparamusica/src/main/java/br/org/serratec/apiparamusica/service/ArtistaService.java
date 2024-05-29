package br.org.serratec.apiparamusica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.apiparamusica.dto.ArtistaDto;
import br.org.serratec.apiparamusica.model.Artista;
import br.org.serratec.apiparamusica.repository.ArtistaRepository;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public ArtistaDto adicionarArtista(ArtistaDto artistaDto) {
        Artista artista = new Artista();
        artista.setNome(artistaDto.nome());
        artista.setGenero(artistaDto.genero());

        artista = artistaRepository.save(artista);
        return new ArtistaDto(artista.getId(), artista.getNome(), artista.getGenero(),
                artista.getAlbuns().stream().map(album -> album.getId()).collect(Collectors.toList()));
    }

    public List<ArtistaDto> listarArtistas() {
        return artistaRepository.findAll().stream().map(artista -> 
            new ArtistaDto(artista.getId(), artista.getNome(), artista.getGenero(),
                    artista.getAlbuns().stream().map(album -> album.getId()).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    public Optional<ArtistaDto> obterArtistaPorId(Long id) {
        return artistaRepository.findById(id).map(artista -> 
            new ArtistaDto(artista.getId(), artista.getNome(), artista.getGenero(),
                    artista.getAlbuns().stream().map(album -> album.getId()).collect(Collectors.toList()))
        );
    }

    public List<ArtistaDto> obterArtistaPorGenero(String genero) {
        return artistaRepository.findByGenero(genero).stream().map(artista -> 
            new ArtistaDto(artista.getId(), artista.getNome(), artista.getGenero(),
                    artista.getAlbuns().stream().map(album -> album.getId()).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    public Optional<ArtistaDto> atualizarArtista(Long id, ArtistaDto artistaDto) {
        Optional<Artista> artistaExistente = artistaRepository.findById(id);
        if (artistaExistente.isPresent()) {
            Artista artista = artistaExistente.get();
            artista.setNome(artistaDto.nome());
            artista.setGenero(artistaDto.genero());
            artistaRepository.save(artista);
            return Optional.of(new ArtistaDto(artista.getId(), artista.getNome(), artista.getGenero(),
                    artista.getAlbuns().stream().map(album -> album.getId()).collect(Collectors.toList())));
        }
        return Optional.empty();
    }

    public boolean excluirArtistaPorId(Long id) {
        if (artistaRepository.existsById(id)) {
            artistaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
