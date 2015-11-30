/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.dto;

import java.util.HashMap;

/**
 *
 * @author sudhir
 * @param <String>
 * @param <Object>
 */
public class MediaRequest<String, Object> extends HashMap<String, Object> {

    private static final long serialVersionUID = 7345083202685462380L;

    public void addAllAtributes(HashMap<String, Object> map) {
        this.putAll(map);
    }

    public HashMap<String, Object> getAllAttributes() {
        return this;
    }

    public void addAttribute(String key, Object value) {
        put(key, value);
    }

    public void removeAttribute(String key) {
        remove(key);
    }

    public Object getAttribute(String key) {
        return get(key);
    }
}
