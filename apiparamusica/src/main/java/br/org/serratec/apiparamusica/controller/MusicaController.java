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

import br.org.serratec.apiparamusica.dto.MusicaDto;
import br.org.serratec.apiparamusica.service.MusicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    public ResponseEntity<List<MusicaDto>> listarMusicas() {
        return new ResponseEntity<>(musicaService.listarMusicas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaDto> obterMusicaPorId(@PathVariable Long id) {
        Optional<MusicaDto> dto = musicaService.obterMusicaPorId(id);
        if (dto.isPresent()) {
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar-por-titulo")
    public ResponseEntity<List<MusicaDto>> obterMusicaPorTitulo(@RequestParam String titulo) {
        List<MusicaDto> dto = musicaService.obterMusicaPorTitulo(titulo);
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/buscar-por-genero")
    public ResponseEntity<List<MusicaDto>> obterMusicaPorGenero(@RequestParam String genero) {
        List<MusicaDto> dto = musicaService.obterMusicaPorGenero(genero);
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/buscar-por-album")
    public ResponseEntity<List<MusicaDto>> obterMusicaPorAlbum(@RequestParam String album) {
        List<MusicaDto> dto = musicaService.obterMusicaPorAlbum(album);
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MusicaDto> criarMusica(@RequestBody @Valid MusicaDto musicaDto) {
        return new ResponseEntity<>(musicaService.adicionarMusica(musicaDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaDto> atualizarMusica(@PathVariable Long id, @RequestBody @Valid MusicaDto musicaDto) {
        Optional<MusicaDto> dto = musicaService.atualizarMusica(id, musicaDto);
        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMusica(@PathVariable Long id) {
        if (!musicaService.excluirMusicaPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}