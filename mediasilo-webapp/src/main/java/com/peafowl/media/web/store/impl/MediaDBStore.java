/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.store.impl;

import com.peafowl.media.web.dao.MediaDaoAdapter;
import com.peafowl.media.web.dao.impl.UserDaoImpl;
import com.peafowl.media.web.dao.impl.UserDeviceInfoDaoImpl;
import com.peafowl.media.web.dao.pojo.UserDeviceInfo;
import com.peafowl.media.web.exception.MediaException;
import com.peafowl.media.web.store.StoreAdapter;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class MediaDBStore extends StoreAdapter {

    private UserDaoImpl userDao;
    private UserDeviceInfoDaoImpl userDeviceInfoDao;
    private final Logger log = Logger.getLogger(this.getClass());

    public MediaDaoAdapter getMediaDao() {
        return userDao;
    }

    public void setMediaDao(UserDaoImpl mediaDao) {
        this.userDao = mediaDao;
    }

    public MediaDaoAdapter getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UserDeviceInfoDaoImpl getUserDeviceInfoDao() {
        return userDeviceInfoDao;
    }

    public void setUserDeviceInfoDao(UserDeviceInfoDaoImpl userDeviceInfoDao) {
        this.userDeviceInfoDao = userDeviceInfoDao;
    }

    @Override
    public void provisionUserToDevice(UserDeviceInfo info) throws MediaException {
        try {
            log.info("User Details: " + info.toString());
            if (userDeviceInfoDao == null) {
                log.info("Null---------");
            }
            userDeviceInfoDao.save(info);
        } catch (Exception e) {
            log.error(e,e);
            throw new MediaException(e);
        }
    }

}
