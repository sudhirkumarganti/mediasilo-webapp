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
public class Head {

    private String style = " .darkBackgroundColor {background-color: #171717;}"
            + " .titleBanner {tv-text-style: title1;}"
            + " .subtitleBanner {tv-text-style: title3;}";

    @XmlElement(name = "style")
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
