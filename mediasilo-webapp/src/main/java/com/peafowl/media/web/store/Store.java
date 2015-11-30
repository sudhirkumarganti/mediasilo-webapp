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
public interface Store {

    public Object loadAllAssets(MediaRequest request, MediaResponse response)
            throws MediaException;

    public void isDeviceProvisioned(String deviceId)
            throws MediaException;

    public void provisionUserToDevice(UserDeviceInfo info)
            throws MediaException;

}
