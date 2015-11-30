/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import com.peafowl.media.web.tvml.Document;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@XmlRootElement(name = "document")
public class PTDocument extends Document {

    private ProductTemplate template;

    @XmlElement(name = "productTemplate")
    public ProductTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ProductTemplate template) {
        this.template = template;
    }

}
