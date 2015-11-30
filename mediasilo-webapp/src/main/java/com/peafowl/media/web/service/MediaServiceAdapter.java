/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.service;

import com.peafowl.media.web.action.MediaAction;
import com.peafowl.media.web.action.MediaActionAdapter;
import com.peafowl.media.web.action.impl.MediaActionImpl;
import com.peafowl.media.web.constants.MediaConstants.ActionType;
import static com.peafowl.media.web.constants.MediaConstants.App.ACTION_TYPE;
import com.peafowl.media.web.dto.MediaMessage;
import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.exception.MediaException;
import com.peafowl.media.web.service.impl.MediaServiceImpl;
import com.peafowl.media.web.util.ServiceUtil;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author root
 */
public abstract class MediaServiceAdapter implements MediaService {

    protected final Logger log = Logger.getLogger(MediaServiceImpl.class);
    protected ServiceUtil serviceUtil = new ServiceUtil();
    private String configLocation = "/config/config.properties";
    protected HashMap<String, MediaAction> actionMap;
    protected boolean isDebugEnabled = log.isDebugEnabled();

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public ServiceUtil getServiceUtil() {
        return serviceUtil;
    }

    public void setServiceUtil(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public HashMap<String, MediaAction> getActionMap() {
        return actionMap;
    }

    public void setActionMap(HashMap<String, MediaAction> actionMap) {
        this.actionMap = actionMap;
    }

    @ResponseBody
    @ExceptionHandler(MediaException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MediaMessage handleException(MediaException fe) {
        MediaMessage msg = new MediaMessage();
        msg.setResonse(fe.getMessage());
        msg.setResponseCode(fe.getResponseCode());
        return msg;
    }
    
    protected void populateObjectsToMediaRequest(Object[][] keyValues, MediaRequest request) {
        for (Object[] keyValue : keyValues) {
            String key = (String) keyValue[0];
            Object value = keyValue[1];
            if (isDebugEnabled) {
                log.debug("Key: " + key + " Value: " + value);
            }
            request.addAttribute(key, value);
        }
    }
    
    public final MediaResponse processRequest(Object[][] parameters) throws MediaException {
        MediaRequest request = serviceUtil.getInitializedRequest();
        MediaResponse response = serviceUtil.getInitializedResponse();
        populateObjectsToMediaRequest(parameters, request);
        ActionType type = (ActionType) request.getAttribute(ACTION_TYPE);
        MediaActionAdapter action = new MediaActionImpl();
        action.execute(request, response);
        return response;
    }
}
