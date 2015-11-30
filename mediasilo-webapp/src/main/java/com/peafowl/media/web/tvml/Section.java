/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml;

import com.peafowl.media.web.tvml.st.Lockup;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Section {

    private List<Lockup> lockup;

    @XmlElement(name = "lockup")
    public List<Lockup> getLockup() {
        return lockup;
    }

    public void setLockup(List<Lockup> lockup) {
        this.lockup = lockup;
    }

}
