package com.codegym.service;

import java.util.ArrayList;
import java.util.List;

public interface GeneralService<E> {
    List<E> findAll();
    void save(E e);
    E findById(int id);
    List<E> findByName(String name);
    void update(int id,E e);
    void remove(int id);
}
