package com.concert.agency.dao;

public interface AbstractDao<T> {
    T add(T entity);
}
