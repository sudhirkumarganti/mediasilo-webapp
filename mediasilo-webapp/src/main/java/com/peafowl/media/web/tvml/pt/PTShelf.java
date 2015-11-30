/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import com.peafowl.media.web.tvml.Header;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class PTShelf {
    private Header header;
    private PTShelf shelf;
    
    @XmlElement(name = "header")
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlElement(name ="shelf")
    public PTShelf getShelf() {
        return shelf;
    }

    public void setShelf(PTShelf shelf) {
        this.shelf = shelf;
    }
    
}
