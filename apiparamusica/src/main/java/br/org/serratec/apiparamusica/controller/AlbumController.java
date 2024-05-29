package br.org.serratec.apiparamusica.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.apiparamusica.dto.AlbumDto;
import br.org.serratec.apiparamusica.service.AlbumService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/albuns")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumDto>> listarAlbuns() {
        List<AlbumDto> albuns = albumService.listarAlbuns();
        return new ResponseEntity<>(albuns, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> obterAlbumPorId(@PathVariable Long id) {
        Optional<AlbumDto> dto = albumService.obterAlbumPorId(id);
        if (dto.isPresent()) {
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar-por-artista")
    public ResponseEntity<List<AlbumDto>> obterAlbumPorArtista(@RequestParam String nomeArtista) {
        List<AlbumDto> dto = albumService.obterAlbumPorArtista(nomeArtista);
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumDto> criarAlbum(@RequestBody @Valid AlbumDto albumDto) {
        AlbumDto albumCriado = albumService.adicionarAlbum(albumDto);
        return new ResponseEntity<>(albumCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDto> atualizarAlbum(@PathVariable Long id, @RequestBody @Valid AlbumDto albumDto) {
        Optional<AlbumDto> dto = albumService.atualizarAlbum(id, albumDto);
        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAlbum(@PathVariable Long id) {
        if (!albumService.excluirAlbumPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}