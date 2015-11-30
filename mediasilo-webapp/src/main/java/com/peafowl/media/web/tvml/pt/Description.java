/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author root
 */
public class Description {

    private boolean allowsZooming;
    private String moreLabel = "more";
    private String description;

    @XmlAttribute(name = "allowsZooming")
    public boolean isAllowsZooming() {
        return allowsZooming;
    }

    public void setAllowsZooming(boolean allowsZooming) {
        this.allowsZooming = allowsZooming;
    }

    @XmlAttribute(name = "moreLabel")
    public String getMoreLabel() {
        return moreLabel;
    }

    public void setMoreLabel(String moreLabel) {
        this.moreLabel = moreLabel;
    }
    
    @XmlValue
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
