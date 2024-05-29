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

import br.org.serratec.apiparamusica.dto.ArtistaDto;
import br.org.serratec.apiparamusica.service.ArtistaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<ArtistaDto>> listarArtistas() {
        return new ResponseEntity<>(artistaService.listarArtistas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDto> obterArtistaPorId(@PathVariable Long id) {
        Optional<ArtistaDto> dto = artistaService.obterArtistaPorId(id);
        if (dto.isPresent()) {
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar-por-genero")
    public ResponseEntity<List<ArtistaDto>> obterArtistaPorGenero(@RequestParam String genero) {
        List<ArtistaDto> dto = artistaService.obterArtistaPorGenero(genero);
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArtistaDto> criarArtista(@RequestBody @Valid ArtistaDto artistaDto) {
        return new ResponseEntity<>(artistaService.adicionarArtista(artistaDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDto> atualizarArtista(@PathVariable Long id, @RequestBody @Valid ArtistaDto artistaDto) {
        Optional<ArtistaDto> dto = artistaService.atualizarArtista(id, artistaDto);
        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirArtista(@PathVariable Long id) {
        if (!artistaService.excluirArtistaPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}