/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import com.peafowl.media.web.tvml.Shelf;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class CollectionList {

    private Carousel carousel;
    private Shelf shelf;

    @XmlElement(name = "shelf")
    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
    

    @XmlElement(name = "carousel")
    public Carousel getCarousel() {
        return carousel;
    }

    public void setCarousel(Carousel carousel) {
        this.carousel = carousel;
    }

}
