/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Lockup {

    private String videoURL;
    private Image image;
    private Overlay overlay;
    
    @XmlElement(name = "overlay")
    public Overlay getOverlay() {
        return overlay;
    }

    public void setOverlay(Overlay overlay) {
        this.overlay = overlay;
    }
    
    @XmlAttribute(name = "videoURL")
    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String id) {
        this.videoURL = id;
    }
    @XmlElement(name = "img")
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
