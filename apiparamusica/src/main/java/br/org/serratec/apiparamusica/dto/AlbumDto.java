package br.org.serratec.apiparamusica.dto;

import java.util.List;

public record AlbumDto (
	Long id,
	String nome,
	String dataLancamento,
	Long artistaId,
	List<Long> musicasIds
	 
) {}
