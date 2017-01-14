package org.plutoz.pricedepo.common.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractCrudService<T, I extends Serializable> implements CrudService<T, I> {

	private PagingAndSortingRepository<T, I> repository;
	
	public AbstractCrudService(PagingAndSortingRepository<T, I> repository){
		this.repository = repository;
	}
	
	@Override
	public T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<T> save(Iterable<T> entities) {
		return repository.save(entities);
	}

	@Override
	public T findOne(I id) {
		return repository.findOne(id);
	}

	@Override
	public Iterable<T> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Iterable<T> findAll(Sort sort){
		return repository.findAll(sort);
	}
	
	@Override
	public Page<T> findAll(Pageable pageable){
		return repository.findAll(pageable);
	} 

	@Override
	public Iterable<T> findAll(Iterable<I> ids) {
		return repository.findAll(ids);
	}

	@Override
	public boolean exists(I id) {
		return repository.exists(id);
	}

	@Override
	public long count() {
		return repository.count();
	}
	
	@Override
	public void delete(I id){
		repository.delete(id);
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		repository.delete(entities);
		
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
		
	}

}
