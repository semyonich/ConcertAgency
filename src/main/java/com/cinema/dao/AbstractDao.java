package com.cinema.dao;

public interface AbstractDao<T> {
    T add(T entity);
}
