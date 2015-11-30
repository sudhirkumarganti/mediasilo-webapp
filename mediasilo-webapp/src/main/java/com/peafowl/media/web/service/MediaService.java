/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.service;

import com.peafowl.media.web.tvml.Document;
import com.peafowl.media.web.exception.MediaException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public interface MediaService {

    public Document loadMrssAtAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException;

    public Document loadMrssPtAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException;

    public Document loadApiStAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException;

    public Document loadApiPtAssets(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse)
            throws MediaException;

    public void isDeviceProvisioned(HttpServletResponse response, String deviceId)
            throws MediaException;
    
    public Document loadSignupUserForm()
            throws MediaException;
    
    public Document provisionDeviceToUser(String deviceId, String mediaSiloId, String password)
            throws MediaException;

}
