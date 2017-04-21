package org.plutoz.pricedepo.common.rest.dto.converter;

public interface DtoConverter <DTO, MODEL> {
	
	DTO toDto( MODEL model );
	MODEL toModel( DTO dto );
}
