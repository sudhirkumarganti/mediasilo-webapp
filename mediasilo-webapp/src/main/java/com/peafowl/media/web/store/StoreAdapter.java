/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.store;

import com.peafowl.media.web.dao.pojo.UserDeviceInfo;
import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.exception.MediaException;

/**
 *
 * @author root
 */
public abstract class StoreAdapter implements Store {

    @Override
    public Object loadAllAssets(MediaRequest request, MediaResponse response) throws MediaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void isDeviceProvisioned(String deviceId) throws MediaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void provisionUserToDevice(UserDeviceInfo info) throws MediaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
