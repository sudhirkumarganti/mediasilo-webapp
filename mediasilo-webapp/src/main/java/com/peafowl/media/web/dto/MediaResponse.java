/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.dto;

import java.util.HashMap;

/**
 *
 * @author sudhir
 */
public class MediaResponse extends HashMap {

    private static final long serialVersionUID = -624772443658344813L;
    public static final String RESPONSE = "response";
    public static final String RESPONSE_CODE = "responceCode";

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
