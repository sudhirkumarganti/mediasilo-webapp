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
public class Shelf {

    private Section section;
    private Header header;
    
    @XmlElement(name = "header")
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
    
    @XmlElement(name = "section")
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

}
