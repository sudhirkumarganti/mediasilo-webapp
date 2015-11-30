/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.st;

import com.peafowl.media.web.tvml.Document;
import com.peafowl.media.web.tvml.pt.Banner;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@XmlRootElement(name = "document")
public class STDocument extends Document {

    private StackTemplate stackTemplate;
    private Banner banner;
    private Head head;

    @XmlElement(name = "head")
    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public void setStackTemplate(StackTemplate stackTemplate) {
        this.stackTemplate = stackTemplate;
    }

    @XmlElement(name = "stackTemplate")
    public StackTemplate getStackTemplate() {
        return stackTemplate;
    }

}
