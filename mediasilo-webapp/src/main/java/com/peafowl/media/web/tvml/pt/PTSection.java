/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class PTSection {
    
    private List<MonogramLookup> monogroupLookup;

    @XmlElement(name="monogramLockup")
    public List<MonogramLookup> getMonogroupLookup() {
        return monogroupLookup;
    }

    public void setMonogroupLookup(List<MonogramLookup> monogroupLookup) {
        this.monogroupLookup = monogroupLookup;
    }
    
}
