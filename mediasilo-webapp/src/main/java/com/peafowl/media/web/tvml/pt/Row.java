/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.tvml.pt;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author root
 */
public class Row {

    private List<String> textList;
    private List<Badge> badgeList;
    private List<ButtonLockup> buttonLockupList;

    @XmlElement(name = "text")
    public List<String> getTextList() {
        return textList;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    @XmlElement(name = "badge")
    public List<Badge> getBadgeList() {
        return badgeList;
    }

    public void setBadgeList(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }

    @XmlElement(name = "buttonLockup")
    public List<ButtonLockup> getButtonLockupList() {
        return buttonLockupList;
    }

    public void setButtonLockupList(List<ButtonLockup> buttonLockupList) {
        this.buttonLockupList = buttonLockupList;
    }

}
