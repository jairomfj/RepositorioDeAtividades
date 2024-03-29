package br.com.repositoriodeatividades.repositories.interfaces;

public interface GenericRepositoryInterface<T> {
    T save(T emp);
    Boolean delete(T emp);
    T edit(T emp);
    T find(Long empId);
}