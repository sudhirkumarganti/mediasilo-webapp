/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.dao.impl;

import com.peafowl.media.web.dao.MediaDaoAdapter;
import org.springframework.dao.DataAccessException;
import com.peafowl.media.web.dao.pojo.UserDeviceInfo;

/**
 *
 * @author root
 */
public class UserDeviceInfoDaoImpl extends MediaDaoAdapter<UserDeviceInfo, Long> {

    @Override
    public void save(UserDeviceInfo newInstance) throws DataAccessException {
       template.update("insert into user_device_info (deviceid,mediasilo_id) values (?,?)", newInstance.getDeviceId(),newInstance.getMediaSiloId());
    }
}
