/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.store.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.MEDIASILO_HOST_CONTEXT;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.PASSWORD;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.USER_ID;
import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.tvml.st.Channel;
import com.peafowl.media.web.tvml.st.Item;
import com.peafowl.media.web.exception.MediaException;
import com.peafowl.media.web.store.StoreAdapter;
import com.peafowl.media.web.util.ServiceUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author root
 */
public class MediaSiloApiStore extends StoreAdapter {

    private static final Logger log = Logger.getLogger(MediaSiloRssStore.class);
    private final RestTemplate restClient;

    static {
        try {
            ServiceUtil.loadAllCertificates();
        } catch (Exception e) {
            log.error(e);
        }
    }

    public MediaSiloApiStore() {
        restClient = new RestTemplate();
    }

    @Override
    public Channel loadAllAssets(MediaRequest request, MediaResponse response) throws MediaException {
        try {
            String userId = (String) request.getAttribute(USER_ID);
            String password = (String) request.getAttribute(PASSWORD);
            String url = (String) request.getAttribute(URL);
            String mediaSiloHostContext = (String) request.getAttribute(MEDIASILO_HOST_CONTEXT);
            HttpHeaders headers = new HttpHeaders();
            String encodedCredentials = Base64.encode(String.format("%s:%s", userId, password).getBytes());
            headers.add(MEDIASILO_HOST_CONTEXT, mediaSiloHostContext);
            headers.add(HttpHeaders.AUTHORIZATION, String.format("Basic %s", encodedCredentials));
            HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
            ResponseEntity<String> responseEntity = restClient.exchange(url, HttpMethod.GET, requestEntity, String.class);
            String payload = (String) responseEntity.getBody();
            log.info(payload);
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> map = mapper.readValue(payload, List.class);
            Channel channel = new Channel();
            for (Map<String, Object> details : map) {
                Item item = new Item();
                item.setContent(new HashMap<String, String>());
                for (String key : details.keySet()) {
                    log.info("Key: " + key);
                    putDetailsToItem(item, key, details.get(key));
                }
                channel.setItem(item);
            }
            return channel;
        } catch (RestClientException | IOException e) {
            log.error(e);
            throw new MediaException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e);
        }
    }

    private void putDetailsToItem(Item item, String key, Object value) throws MediaException {
        if (value != null) {
            switch (key) {
                case "uploadedBy":
                    item.getContent().put(key, String.valueOf(value));
                    break;
                case "type":
                    item.getContent().put(key, String.valueOf(value));
                    break;
                case "derivatives":
                    List<Map<String, Object>> list = (List) value;
                    putDetailsToItem(item, "derivativesMap", list);
                    break;
                case "url":
                    item.setLink((String) value);
                    item.getContent().put(key, String.valueOf(value));
                    break;
                case "width":
                    item.getContent().put(key, String.valueOf(value));
                    break;
                case "height":
                    item.getContent().put(key, String.valueOf(value));
                    break;
                case "title":
                    item.setTitle((String) value);
                    break;
                case "posterFrame":
                    item.setThumbNail((String) value);
                    break;
                case "description":
                    item.setDescription((String) value);
                    break;
                case "duration":
                    item.getContent().put(key, String.valueOf(value));
                    break;
                case "derivativesthumbnail":
                    item.getContent().put("posterFrame", (String) value);
                    break;
                case "derivativesMap":
                    processDerrivatives(item, key, value);
                    break;
            }
        }
    }

    private void processDerrivatives(Item item, String key, Object value) throws MediaException {
        List<Map<String, Object>> listMapValue = (List) value;
        for (Map<String, Object> derivativesMap : listMapValue) {
            log.info("Derivatives Keys: " + derivativesMap.keySet());
            switch ((String) derivativesMap.get("type")) {
                case "source": {
                    putDetailsToItem(item, "url", derivativesMap.get("url"));
                    putDetailsToItem(item, "width", derivativesMap.get("width"));
                    putDetailsToItem(item, "height", derivativesMap.get("height"));
                    break;
                }
                case "proxy": {
                    putDetailsToItem(item, "derivativesthumbnail", derivativesMap.get("thumbnail"));
                    break;
                }
            }
        }
    }
}
