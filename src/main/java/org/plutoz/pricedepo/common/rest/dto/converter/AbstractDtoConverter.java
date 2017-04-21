package org.plutoz.pricedepo.common.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

public abstract class AbstractDtoConverter <DTO, MODEL> implements DtoConverter<DTO, MODEL> {

	private final Class<MODEL> modelClazz;
	private final Class<DTO> dtoClazz;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@SuppressWarnings("unchecked")
	public AbstractDtoConverter() {
		this.dtoClazz = (Class<DTO>) GenericTypeResolver.resolveTypeArguments(getClass(), AbstractDtoConverter.class)[0];
		this.modelClazz = (Class<MODEL>) GenericTypeResolver.resolveTypeArguments(getClass(), AbstractDtoConverter.class)[1];
	}
	
	@Override
	public DTO toDto( MODEL model ) {
		return modelMapper.map( model, dtoClazz );
	}

	@Override
	public MODEL toModel( DTO dto ) {
		return modelMapper.map( dto, modelClazz );
	}
}
