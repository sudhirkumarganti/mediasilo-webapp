/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sudhir
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class MediaMessage {

    //Estate Restful methods specific messages
    public static final String ERROR_WHILE_GET_ASSETS = "Error occured while getting the assets";
    @XmlElement(name = "responseCode")
    int responseCode;
    @XmlElement(name = "response")
    Object response;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResonse(Object response) {
        this.response = response;
    }
}
