/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.lt;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class FormTemplate {

    private FTBanner banner;
    private List<TextField> textField;
    private Footer footer;

    @XmlElement(name = "footer")
    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    @XmlElement(name = "banner")
    public FTBanner getBanner() {
        return banner;
    }

    public void setBanner(FTBanner banner) {
        this.banner = banner;
    }

    @XmlElement(name = "textField")
    public List<TextField> getTextField() {
        return textField;
    }

    public void setTextField(List<TextField> textField) {
        this.textField = textField;
    }

}
