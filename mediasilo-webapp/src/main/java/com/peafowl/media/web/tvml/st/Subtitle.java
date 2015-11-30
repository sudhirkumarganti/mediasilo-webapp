/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author root
 */
public class Subtitle {
    
    @XmlValue
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    private String subTitle;
    private boolean allowsZooming;
    
    @XmlAttribute(name ="allowsZooming")
    public boolean isAllowsZooming() {
        return allowsZooming;
    }

    public void setAllowsZooming(boolean allowsZooming) {
        this.allowsZooming = allowsZooming;
    }
    
}
