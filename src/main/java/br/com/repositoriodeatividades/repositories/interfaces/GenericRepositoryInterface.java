package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.User;

import java.util.List;

public interface GenericRepositoryInterface<T> {
    public T save(T emp);
    public Boolean delete(T emp);
    public T edit(T emp);
    public T find(Long empId);
}