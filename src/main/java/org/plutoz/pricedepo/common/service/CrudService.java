package org.plutoz.pricedepo.common.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CrudService <T, I extends Serializable> {

	public T save(T entity);
	
	public Iterable<T> save(Iterable<T> entities);
	
	public T findOne(I id);
	
	public Iterable<T> findAll();
	
	public Iterable<T> findAll(Sort sort);
	
	Page<T> findAll(Pageable pageable);
	
	public Iterable<T> findAll(Iterable<I> ids);
	
	public boolean exists(I id);
	
	public long count();
	
	public void delete(I id);
	
	public void delete(T entity);
	
	public void delete(Iterable<? extends T> entities);
	
	public void deleteAll();
}
