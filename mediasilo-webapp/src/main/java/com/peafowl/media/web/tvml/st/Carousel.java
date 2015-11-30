/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import com.peafowl.media.web.tvml.Section;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Carousel {
    
    private Section section;

    @XmlElement(name = "section")
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    
}
