/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author root
 * @param <E>
 * @param <PK>
 */
public interface MediaDao<E, PK> {
    
    public void save(E newInstance) throws DataAccessException;

    public E findById(PK id) throws DataAccessException;
    
    public List<E> find(String query) throws DataAccessException;

    public List<E> find(String query, int maxResults) throws DataAccessException;
}
