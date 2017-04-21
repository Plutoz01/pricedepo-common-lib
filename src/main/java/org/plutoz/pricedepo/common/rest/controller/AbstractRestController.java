package org.plutoz.pricedepo.common.rest.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.plutoz.pricedepo.common.domain.Identifiable;
import org.plutoz.pricedepo.common.rest.dto.converter.DtoConverter;
import org.plutoz.pricedepo.common.rest.exception.ResourceNotFoundException;
import org.plutoz.pricedepo.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractRestController<ID extends Serializable, E extends Identifiable<ID>, DTO extends Identifiable<ID>> {
	
	@Autowired
	private CrudService<ID, E> crudService;
	@Autowired
	private DtoConverter<DTO, E> dtoConverter;
	
	@GetMapping
	public Page<DTO> getAll(Pageable pageable){
		Page<E> entities = crudService.findAll(pageable);
		List<DTO> dtos = entities.getContent()
				.stream()
				.map( dtoConverter::toDto )
				.collect(Collectors.toList());
		
		return new PageImpl<>(dtos);
	}

	@GetMapping("/{id}")
	public DTO findById(@PathVariable("id") ID id){
		E result = crudService.findOne(id);
		
		if(result == null){
			throw new ResourceNotFoundException();
		}
		
		return dtoConverter.toDto( result );
	}
	
	@PostMapping
	public DTO create(@RequestBody DTO dto){
		E entity = dtoConverter.toModel( dto );		
		E savedEntity = crudService.save(entity);		
		return dtoConverter.toDto( savedEntity );
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ID id){
		crudService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DTO> update(@PathVariable ID id, @RequestBody DTO dto){
		if(!crudService.exists(id)){
			return ResponseEntity.notFound().build();
		}
		
		E entity = dtoConverter.toModel( dto );		
		entity.setId(id);
		
		E updated = crudService.save(entity);		
		return ResponseEntity.ok( dtoConverter.toDto( updated ) );
	}	
	
}
