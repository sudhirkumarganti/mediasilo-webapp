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
public class FTBanner {
    
    private String description;

    @XmlElement(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
