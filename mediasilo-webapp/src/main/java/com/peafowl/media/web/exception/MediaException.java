/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.exception;

import java.util.HashMap;
import org.springframework.http.HttpStatus;

/**
 *
 * @author root
 */
public class MediaException extends Exception {
    
    private static final long serialVersionUID = 1L;
    private String msg;
    private int responseCode = HttpStatus.BAD_REQUEST.value();
    private HashMap<Object, Object> metaInfo = new HashMap<>();

    public MediaException(Throwable error) {
        super(error);

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public MediaException(String message) {
        super(message);
        this.msg = message;
    }

    public MediaException(String message, int code) {
        super(message);
        this.msg = message;
        this.responseCode = code;
    }

    public MediaException(String message, Throwable error) {
        super(message, error);
    }

    public void addMetaData(Object key, Object value) {
        this.metaInfo.put(key, value);
    }

    public HashMap<Object, Object> getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(HashMap<Object, Object> metaInfo) {
        this.metaInfo = metaInfo;
    }

}
