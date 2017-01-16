package org.plutoz.pricedepo.common.service;

import java.io.Serializable;

import org.plutoz.pricedepo.common.domain.Identifiable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CrudService <ID extends Serializable, T extends Identifiable<ID>> {

	public T save(T entity);
	
	public Iterable<T> save(Iterable<T> entities);
	
	public T findOne(ID id);
	
	public Iterable<T> findAll();
	
	public Iterable<T> findAll(Sort sort);
	
	Page<T> findAll(Pageable pageable);
	
	public Iterable<T> findAll(Iterable<ID> ids);
	
	public boolean exists(ID id);
	
	public long count();
	
	public void delete(ID id);
	
	public void delete(T entity);
	
	public void delete(Iterable<? extends T> entities);
	
	public void deleteAll();
}
