/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.action;

import static com.peafowl.media.web.constants.MediaConstants.App.CONFIGURATION_PATH;
import static com.peafowl.media.web.constants.MediaConstants.App.STORE_TYPE;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.API_URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.MRSS_URL;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.TEMPLATE_TYPE;
import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.exception.MediaException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public abstract class MediaActionAdapter implements MediaAction {

    protected final Logger log = Logger.getLogger(this.getClass());
    protected Properties prop;

    protected void populateMsgAndThorwException(String errMsg) throws MediaException {
        log.error(errMsg);
        throw new MediaException(errMsg);
    }

    protected void validateMandatoryParameters(MediaRequest request, MediaResponse response) throws MediaException {
        try {
            log.info("Validating Mandatory Request Parameters");
            checkForValidConfigurationPath(request);
            loadProperties(request);
            checkForValidCustomerName(request);
            checkForValidTemplateType(request);
            checkForValidStoreType(request);
        } catch (IOException e) {
            throw new MediaException(e);
        }
    }

    protected void populateObjectsToMediaRequest(Object[][] keyValues, MediaRequest request) {
        for (Object[] keyValue : keyValues) {
            String key = (String) keyValue[0];
            Object value = keyValue[1];
            if (log.isDebugEnabled()) {
                log.debug("Key: " + key + " Value: " + value);
            }
            request.addAttribute(key, value);
        }
    }

    protected void loadProperties(MediaRequest request) throws IOException {
        String configLocation = (String) request.getAttribute(CONFIGURATION_PATH);
        if (prop == null) {
            prop = new Properties();
            prop.load(new FileInputStream(configLocation));
            log.debug(prop.toString());
        }
    }

    protected void checkForValidConfigurationPath(MediaRequest request) throws MediaException {
        if (request.getAttribute(CONFIGURATION_PATH) == null) {
            populateMsgAndThorwException("Invalid (Null??) Configuration Type");
        }
    }

    protected void checkForValidStoreType(MediaRequest request) throws MediaException {
        if (request.getAttribute(STORE_TYPE) == null) {
            populateMsgAndThorwException("Invalid (Null??) Store Type ");
        }
    }

    protected String checkConfiguredCustomerName(String customerURL, MediaRequest request) throws MediaException {
        String customerName = (String) request.getAttribute(customerURL);
        String configuredCustomerName = prop.getProperty(customerName);
        if (configuredCustomerName == null) {
            populateMsgAndThorwException("Invalid (Null??) configured MRSS customer url");
        }
        return configuredCustomerName;
    }

    protected void checkForValidTemplateType(MediaRequest request) throws MediaException {
        if (request.getAttribute(TEMPLATE_TYPE) == null) {
            populateMsgAndThorwException("Invalid (Null??) Template Type ");
        }
    }

    protected void checkForValidCustomerName(MediaRequest request) throws MediaException {
        if (request.getAttribute(API_URL) == null) {
            populateMsgAndThorwException("Invalid (Null??) API name/url");
        }
        if (request.getAttribute(MRSS_URL) == null) {
            populateMsgAndThorwException("Invalid (Null??) MRSS name/url");
        }
    }

    @Override
    public abstract void execute(MediaRequest request, MediaResponse response) throws MediaException;

}
