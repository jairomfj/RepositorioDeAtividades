package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.User;

import java.util.List;

public interface GenericRepositoryInterface<T> {
    T save(T emp);
    Boolean delete(T emp);
    T edit(T emp);
    T find(Long empId);
}