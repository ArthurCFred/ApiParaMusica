package br.org.serratec.apiparamusica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.apiparamusica.dto.MusicaDto;
import br.org.serratec.apiparamusica.model.Album;
import br.org.serratec.apiparamusica.model.Musica;
import br.org.serratec.apiparamusica.repository.MusicaRepository;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    public MusicaDto adicionarMusica(MusicaDto musicaDto) {
        Musica musica = new Musica();
        musica.setTitulo(musicaDto.titulo());
        musica.setGenero(musicaDto.genero());
        musica.setDuracao(musicaDto.duracao());
        Album album = new Album();
        album.setId(musicaDto.albumId());
        musica.setAlbum(album);

        musica = musicaRepository.save(musica);
        return new MusicaDto(musica.getId(), musica.getTitulo(), musica.getGenero(), musica.getDuracao(), musica.getAlbum().getId());
    }

    public List<MusicaDto> listarMusicas() {
        return musicaRepository.findAll().stream().map(musica -> 
            new MusicaDto(musica.getId(), musica.getTitulo(), musica.getGenero(), musica.getDuracao(), musica.getAlbum().getId())
        ).collect(Collectors.toList());
    }

    public Optional<MusicaDto> obterMusicaPorId(Long id) {
        return musicaRepository.findById(id).map(musica -> 
            new MusicaDto(musica.getId(), musica.getTitulo(), musica.getGenero(), musica.getDuracao(), musica.getAlbum().getId())
        );
    }

    public List<MusicaDto> obterMusicaPorTitulo(String titulo) {
        return musicaRepository.findByTitulo(titulo).stream().map(musica -> 
            new MusicaDto(musica.getId(), musica.getTitulo(), musica.getGenero(), musica.getDuracao(), musica.getAlbum().getId())
        ).collect(Collectors.toList());
    }

    public List<MusicaDto> obterMusicaPorGenero(String genero) {
        return musicaRepository.findByGenero(genero).stream().map(musica -> 
            new MusicaDto(musica.getId(), musica.getTitulo(), musica.getGenero(), musica.getDuracao(), musica.getAlbum().getId())
        ).collect(Collectors.toList());
    }

    public List<MusicaDto> obterMusicaPorAlbum(String nomeAlbum) {
        return musicaRepository.findByAlbumNome(nomeAlbum).stream().map(musica -> 
            new MusicaDto(musica.getId(), musica.getTitulo(), musica.getGenero(), musica.getDuracao(), musica.getAlbum().getId())
        ).collect(Collectors.toList());
    }

    public Optional<MusicaDto> atualizarMusica(Long id, MusicaDto musicaDto) {
        Optional<Musica> musicaExistente = musicaRepository.findById(id);
        if (musicaExistente.isPresent()) {
            Musica musica = musicaExistente.get();
            musica.setTitulo(musicaDto.titulo());
            musica.setGenero(musicaDto.genero());
            musica.setDuracao(musicaDto.duracao());
            Album album = new Album();
            album.setId(musicaDto.albumId());
            musica.setAlbum(album);
            musicaRepository.save(musica);
            return Optional.of(new MusicaDto(musica.getId(), musica.getTitulo(), musica.getGenero(), musica.getDuracao(), musica.getAlbum().getId()));
        }
        return Optional.empty();
    }

    public boolean excluirMusicaPorId(Long id) {
        if (musicaRepository.existsById(id)) {
            musicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
