/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.service.impl;

import com.peafowl.media.web.constants.MediaConstants.ActionType;
import static com.peafowl.media.web.constants.MediaConstants.ActionType.API_METADATA;
import static com.peafowl.media.web.constants.MediaConstants.ActionType.MRSS_METADATA;
import static com.peafowl.media.web.constants.MediaConstants.App.ACTION_TYPE;
import static com.peafowl.media.web.constants.MediaConstants.App.CONFIGURATION_PATH;
import static com.peafowl.media.web.constants.MediaConstants.App.STORE_TYPE;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.API_URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.DEFAULT_CUSTOMER_API_URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.DEFAULT_CUSTOMER_MRSS_URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.MRSS_URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.PASSWORD;
import static com.peafowl.media.web.constants.MediaConstants.StoreType.API;
import static com.peafowl.media.web.constants.MediaConstants.StoreType.MRSS;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.FORM_TEMPLATE;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.PRODUCT_TEMPLATE;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.STACK_TEMPLATE;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.TEMPLATE_TYPE;
import static com.peafowl.media.web.constants.MediaConstants.UserDeviceInfo.DEVICE_ID;
import static com.peafowl.media.web.constants.MediaConstants.UserDeviceInfo.MEDIASILO_ID;
import static com.peafowl.media.web.dto.MediaResponse.RESPONSE;
import com.peafowl.media.web.tvml.Document;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.exception.MediaException;
import com.peafowl.media.web.service.MediaServiceAdapter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sudhir
 */
@Controller
public class MediaServiceImpl extends MediaServiceAdapter {

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/metadata/pt/loadAssets")
    @Override
    public Document loadApiPtAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException {
        //product template
        log.info("Loading Assets in progress");
        ServletContext context = httpRequest.getServletContext();
        /**
         * <pre>
         * Add Following request attributes
         * ================================
         * 1. TEMPLATE_TYPE
         * 2. STORE_TYPE
         * 3. CONFIGURATION_PATH
         * 4. API_URL. Default (DEFAULT_CUSTOMER_URL = defaultCustomerApiUrl)
         * 5. MRSS_URL
         * </pre>
         */
        MediaResponse response = processRequest(new Object[][]{
            {ACTION_TYPE, API_METADATA},
            {TEMPLATE_TYPE, PRODUCT_TEMPLATE},
            {STORE_TYPE, API},
            {CONFIGURATION_PATH, context.getRealPath(getConfigLocation())},
            {API_URL, DEFAULT_CUSTOMER_API_URL},
            {MRSS_URL, DEFAULT_CUSTOMER_MRSS_URL}
        });
        return (Document) response.getAttribute(RESPONSE);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/metadata/st/loadAssets")
    @Override
    public Document loadApiStAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException {
        // standard template
        log.info("Loading Assets in progress");
        ServletContext context = httpRequest.getServletContext();
        MediaResponse response = processRequest(new Object[][]{
            {ACTION_TYPE, API_METADATA},
            {TEMPLATE_TYPE, STACK_TEMPLATE},
            {STORE_TYPE, API},
            {CONFIGURATION_PATH, context.getRealPath(getConfigLocation())},
            {API_URL, DEFAULT_CUSTOMER_API_URL},
            {MRSS_URL, DEFAULT_CUSTOMER_MRSS_URL}
        });
        return (Document) response.getAttribute(RESPONSE);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/metadata/mrss/pt/loadAssets")
    @Override
    public Document loadMrssPtAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException {
        //product template
        log.info("Loading Assets in progress");
        ServletContext context = httpRequest.getServletContext();
        MediaResponse response = processRequest(new Object[][]{
            {ACTION_TYPE, MRSS_METADATA},
            {TEMPLATE_TYPE, PRODUCT_TEMPLATE},
            {STORE_TYPE, MRSS},
            {CONFIGURATION_PATH, context.getRealPath(getConfigLocation())},
            {API_URL, DEFAULT_CUSTOMER_API_URL},
            {MRSS_URL, DEFAULT_CUSTOMER_MRSS_URL}
        });
        return (Document) response.getAttribute(RESPONSE);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/metadata/mrss/st/loadAssets")
    @Override
    public Document loadMrssAtAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException {
        //product template
        log.info("Loading Assets in progress");
        ServletContext context = httpRequest.getServletContext();
        MediaResponse response = processRequest(new Object[][]{
            {ACTION_TYPE, MRSS_METADATA},
            {TEMPLATE_TYPE, STACK_TEMPLATE},
            {STORE_TYPE, MRSS},
            {CONFIGURATION_PATH, context.getRealPath(getConfigLocation())},
            {API_URL, DEFAULT_CUSTOMER_API_URL},
            {MRSS_URL, DEFAULT_CUSTOMER_MRSS_URL}
        });
        return (Document) response.getAttribute(RESPONSE);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/device/check/{deviceId}")
    @Override
    public void isDeviceProvisioned(HttpServletResponse httpResponse,
            @PathVariable("deviceId") String deviceId)
            throws MediaException {
        log.info("Checking Device Provisioned Status");
        MediaResponse response = processRequest(new Object[][]{
            {ACTION_TYPE, ActionType.USER_DEVICE_PAIRING_CHECK},
            {DEVICE_ID, deviceId}
        });
        HttpStatus responseCode = (HttpStatus) response.getAttribute(MediaResponse.RESPONSE_CODE);
        httpResponse.setStatus(responseCode.value());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/device/signup")
    @Override
    public Document loadSignupUserForm() 
            throws MediaException {
        log.info("Loading Device Provisioning Form");
        MediaResponse response = processRequest(new Object[][]{
            {ACTION_TYPE, ActionType.DEVICE_PROVISIONING_SIGNUP},
            {TEMPLATE_TYPE, FORM_TEMPLATE}
        });
        return (Document) response.getAttribute(RESPONSE);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/device/provision")
    @Override
    public Document provisionDeviceToUser(@RequestParam("deviceId") String deviceId, 
            @RequestParam("mediaSiloId") String mediaSiloId, 
            @RequestParam("password") String password) 
            throws MediaException {
        log.info("Provisioning Device to User in progress");
        MediaResponse response = processRequest(new Object[][]{
            {ACTION_TYPE, ActionType.DEVICE_PROVISIONING_SUBMIT},
            {DEVICE_ID, deviceId},
            {MEDIASILO_ID, mediaSiloId},
            {PASSWORD, password}    
        });
        return (Document) response.getAttribute(RESPONSE);
    }
}
