package br.com.repositoriodeatividades.usecases.interfaces;

public interface GenericRepositoryInterface<T> {
    public T save(T emp);
    public Boolean delete(T emp);
    public T edit(T emp);
    public T find(Long empId);
}