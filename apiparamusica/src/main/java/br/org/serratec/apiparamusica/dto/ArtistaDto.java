package br.org.serratec.apiparamusica.dto;

import java.util.List;

public record ArtistaDto (
	Long id,
    String nome,
    String genero,
    List<Long> albunsIds
    
) {}
