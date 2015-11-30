/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import com.peafowl.media.web.tvml.Shelf;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class ProductTemplate {
        
    private List<Banner> banner;
    private Shelf shelf;
    private PTShelf ptShelf;
    private ProductInfo pInfo;
    
    @XmlElement(name ="banner")
    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }
    
    @XmlElement(name ="shelf")
    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    @XmlElement(name ="shelf")
    public PTShelf getPtShelf() {
        return ptShelf;
    }

    public void setPtShelf(PTShelf ptShelf) {
        this.ptShelf = ptShelf;
    }

    @XmlElement(name ="productInfo")
    public ProductInfo getpInfo() {
        return pInfo;
    }

    public void setpInfo(ProductInfo pInfo) {
        this.pInfo = pInfo;
    }
    
}
