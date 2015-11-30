/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Overlay {
    private String title;
    private Subtitle subTitle;
    
    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @XmlElement(name = "subtitle")
    public Subtitle getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(Subtitle subTitle) {
        this.subTitle = subTitle;
    }
    
}
