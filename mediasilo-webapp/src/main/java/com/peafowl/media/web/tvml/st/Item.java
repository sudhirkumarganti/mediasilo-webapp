/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import java.util.HashMap;

/**
 *
 * @author root
 */
public class Item {
    
    
    private String title;
    private String link;
    private String pubDate;
    private String description;
    private String mediaTitle;
    private String thumbNail;

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap getContent() {
        return content;
    }
    
    public void setContent(HashMap content) {
        this.content = content;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }
    private HashMap content;

    @Override
    public String toString() {
        return "Item{" + "title=" + title + ", link=" + link + ", pubDate=" + pubDate + ", description=" + description + ", content=" + content + ", thumbNail=" + thumbNail + '}';
    }
    
    
}
