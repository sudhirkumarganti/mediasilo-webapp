/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import com.peafowl.media.web.tvml.Header;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Info {

    @XmlElement(name = "header")
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
    private Header header;

    @XmlElement(name = "text")
    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
    private List<String> text;

}
