/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Header {
     
    private String title;
    
    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
