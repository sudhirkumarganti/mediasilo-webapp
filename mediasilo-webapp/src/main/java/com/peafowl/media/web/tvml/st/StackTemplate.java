/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import com.peafowl.media.web.tvml.pt.Banner;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class StackTemplate {

    private List<Banner> banner;
    private CollectionList collectionList;
    private String cssClass = "darkBackgroundColor";
    private String theme ="dark";

    @XmlAttribute(name = "class")
    public String getCssClass() {
        return cssClass;
    }

    @XmlAttribute(name = "theme")
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @XmlElement(name = "collectionList")
    public CollectionList getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(CollectionList collectionList) {
        this.collectionList = collectionList;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

    @XmlElement(name = "banner")
    public List<Banner> getBanner() {
        return banner;
    }

}
