/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Banner {

    private InfoList infoList;
    private Stack stack;
    private HeroImg img;
    private String id;
    
    @XmlAttribute(name ="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @XmlElement(name = "infoList")
    public InfoList getInfoList() {
        return infoList;
    }

    public void setInfoList(InfoList infolist) {
        this.infoList = infolist;
    }

    @XmlElement(name = "stack")
    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }
    
    @XmlElement(name="heroImg")
    public HeroImg getImg() {
        return img;
    }

    public void setImg(HeroImg img) {
        this.img = img;
    }
    
}
