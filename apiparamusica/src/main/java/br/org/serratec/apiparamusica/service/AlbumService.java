package br.org.serratec.apiparamusica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.apiparamusica.dto.AlbumDto;
import br.org.serratec.apiparamusica.model.Album;
import br.org.serratec.apiparamusica.model.Artista;
import br.org.serratec.apiparamusica.repository.AlbumRepository;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumDto adicionarAlbum(AlbumDto albumDto) {
        Album album = new Album();
        album.setNome(albumDto.nome());
        album.setDataLancamento(albumDto.dataLancamento());
        Artista artista = new Artista();
        artista.setId(albumDto.artistaId());
        album.setArtista(artista);

        album = albumRepository.save(album);
        return new AlbumDto(album.getId(), album.getNome(), album.getDataLancamento(), album.getArtista().getId(),
                album.getMusicas().stream().map(musica -> musica.getId()).collect(Collectors.toList()));
    }

    public List<AlbumDto> listarAlbuns() {
        return albumRepository.findAll().stream().map(album -> 
            new AlbumDto(album.getId(), album.getNome(), album.getDataLancamento(), album.getArtista().getId(),
                    album.getMusicas().stream().map(musica -> musica.getId()).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    public Optional<AlbumDto> obterAlbumPorId(Long id) {
        return albumRepository.findById(id).map(album -> 
            new AlbumDto(album.getId(), album.getNome(), album.getDataLancamento(), album.getArtista().getId(),
                    album.getMusicas().stream().map(musica -> musica.getId()).collect(Collectors.toList()))
        );
    }

    public List<AlbumDto> obterAlbumPorArtista(String nomeArtista) {
        return albumRepository.findByArtistaNome(nomeArtista).stream().map(album -> 
            new AlbumDto(album.getId(), album.getNome(), album.getDataLancamento(), album.getArtista().getId(),
                    album.getMusicas().stream().map(musica -> musica.getId()).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    public Optional<AlbumDto> atualizarAlbum(Long id, AlbumDto albumDto) {
        Optional<Album> albumExistente = albumRepository.findById(id);
        if (albumExistente.isPresent()) {
            Album album = albumExistente.get();
            album.setNome(albumDto.nome());
            album.setDataLancamento(albumDto.dataLancamento());
            Artista artista = new Artista();
            artista.setId(albumDto.artistaId());
            album.setArtista(artista);
            albumRepository.save(album);
            return Optional.of(new AlbumDto(album.getId(), album.getNome(), album.getDataLancamento(), album.getArtista().getId(),
                    album.getMusicas().stream().map(musica -> musica.getId()).collect(Collectors.toList())));
        }
        return Optional.empty();
    }

    public boolean excluirAlbumPorId(Long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
