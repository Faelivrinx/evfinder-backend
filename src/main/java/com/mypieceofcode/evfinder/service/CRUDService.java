package com.mypieceofcode.evfinder.service;

import java.util.List;

public interface CRUDService<T> {

    T save(T object);
    void delete(T object);
    List<T> findAll();
    T findById(Long id);
}
