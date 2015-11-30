/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.action;

import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.exception.MediaException;

/**
 *
 * @author root
 */
public interface MediaAction {

    public abstract void execute(MediaRequest request, MediaResponse response) throws MediaException;
}
