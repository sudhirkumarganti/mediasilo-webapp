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
public class ProductInfo {

    private List<InfoTable> infoTable;

    @XmlElement(name = "infoTable")
    public List<InfoTable> getInfoTable() {
        return infoTable;
    }

    public void setInfoTable(List<InfoTable> infoTable) {
        this.infoTable = infoTable;
    }

}
