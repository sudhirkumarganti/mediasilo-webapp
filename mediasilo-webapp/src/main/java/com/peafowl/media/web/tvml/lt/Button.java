/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.lt;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Button {
    
    private String text;

    @XmlElement (name ="text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
