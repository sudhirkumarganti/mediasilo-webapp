/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.lt;

import com.peafowl.media.web.tvml.Document;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@XmlRootElement(name = "document")
public class FTDocument extends Document {
    
    private ArrayList<FormTemplate> formTemplate;

    @XmlElement(name="formTemplate")
    public ArrayList<FormTemplate> getFormTemplate() {
        return formTemplate;
    }

    public void setFormTemplate(ArrayList<FormTemplate> formTemplate) {
        this.formTemplate = formTemplate;
    }
    
}
