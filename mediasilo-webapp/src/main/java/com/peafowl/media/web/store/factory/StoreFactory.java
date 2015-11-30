/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.store.factory;

import com.peafowl.media.web.constants.MediaConstants.StoreType;
import static com.peafowl.media.web.constants.MediaConstants.StoreType.DB;
import com.peafowl.media.web.exception.MediaException;
import com.peafowl.media.web.store.Store;
import com.peafowl.media.web.store.impl.MediaDBStore;
import com.peafowl.media.web.store.impl.MediaSiloApiStore;
import com.peafowl.media.web.store.impl.MediaSiloRssStore;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class StoreFactory {

    private static final Logger log = Logger.getLogger(StoreFactory.class);

    public static Store getInstance(StoreType type) throws MediaException {
        log.info("Loading Store: " + type);
        Store store;
        switch (type) {
            case MRSS: {
                store = new MediaSiloRssStore();
                return store;
            }
            case API: {
                store = new MediaSiloApiStore();
                return store;
            }
            case DB: {
                store = new MediaDBStore();
                return store;
            }
            default: {
                String errMsg = "Invalid Type: " + type;
                log.error(errMsg);
                throw new MediaException(errMsg);
            }
        }
    }
}
