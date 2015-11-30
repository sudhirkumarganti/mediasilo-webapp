/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.dao.pojo;

import java.util.Date;

/**
 *
 * @author root
 */
public class UserDeviceInfo implements java.io.Serializable {
    
    private Long recordId;
    private String deviceId;
    private String activationCode;
    private String deviceStatus;
    private Date activatedDate;
    private Date expiryDate;
    private Date created;
    private Date updated;
    private String mediaSiloId;

    public UserDeviceInfo() {
    }
    
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Date getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getMediaSiloId() {
        return mediaSiloId;
    }

    public void setMediaSiloId(String mediaSiloId) {
        this.mediaSiloId = mediaSiloId;
    }
    
    @Override
    public String toString() {
        return "UserDeviceInfo{" + "recordId=" + recordId 
                + ", deviceId=" + deviceId 
                + ", activationCode=" + activationCode 
                + ", deviceStatus=" + deviceStatus 
                + ", activationDate=" + activatedDate 
                + ", expiryDate=" + expiryDate 
                + ", created=" + created 
                + ", updated=" + updated + '}';
    }

}
