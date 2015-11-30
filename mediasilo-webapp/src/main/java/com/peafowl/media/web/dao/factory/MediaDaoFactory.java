/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.dao.factory;

import com.peafowl.media.web.constants.MediaConstants.DAOType;
import static com.peafowl.media.web.constants.MediaConstants.DAOType.USER_DEVICE_INFO;
import com.peafowl.media.web.dao.MediaDaoAdapter;
import com.peafowl.media.web.dao.impl.UserDeviceInfoDaoImpl;
import com.peafowl.media.web.exception.MediaException;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author root
 */
public class MediaDaoFactory implements ApplicationContextAware {

    private static final Logger log = Logger.getLogger(MediaDaoFactory.class);
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("/media-service.xml");

    public static MediaDaoAdapter getInstance(DAOType type) throws MediaException {
        log.info("Loading DAO: " + type);
        MediaDaoAdapter daoImpl;
        switch (type) {
            case USER_DEVICE_INFO: {
                daoImpl = (UserDeviceInfoDaoImpl) ctx.getBean("userDeviceInfoDaoImpl");
                return daoImpl;
            }
            default: {
                String errMsg = "Invalid DAO Type: " + type;
                log.error(errMsg);
                throw new MediaException(errMsg);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MediaDaoFactory.ctx = applicationContext;
    }
}
