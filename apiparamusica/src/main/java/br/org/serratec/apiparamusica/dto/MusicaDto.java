package br.org.serratec.apiparamusica.dto;

public record MusicaDto (
	Long id,
    String titulo,
    String genero,
    int duracao,
    Long albumId
    ) {}