/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.lt;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Footer {
    
    private Button button;

    @XmlElement(name="button")
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    
}
