/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.store.impl;


import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.URL;
import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.tvml.st.Channel;
import com.peafowl.media.web.tvml.st.Item;
import com.peafowl.media.web.exception.MediaException;
import com.peafowl.media.web.store.StoreAdapter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author root
 */
public class MediaSiloRssStore extends StoreAdapter {

    private final Logger log = Logger.getLogger(MediaSiloRssStore.class);
    
    public String stripNonValidXMLCharacters(String msrssFeed) {
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < msrssFeed.length(); i++) {
            current = msrssFeed.charAt(i);
            if ((current == 0x9)
                    || (current == 0xA)
                    || (current == 0xD)
                    || ((current >= 0x20) && (current <= 0xD7FF))
                    || ((current >= 0xE000) && (current <= 0xFFFD))
                    || ((current >= 0x10000) && (current <= 0x10FFFF))) {
                out.append(current);
            }
        }
        return out.toString();
    }

    @Override
    public Channel loadAllAssets(MediaRequest request, MediaResponse response) throws MediaException {
        try {
            RestTemplate rest = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<>("");
            String url = (String) request.getAttribute(URL);
            ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
            String payload = (String) responseEntity.getBody();
            String validpayload = stripNonValidXMLCharacters(payload);
            log.debug("Response: " + payload);
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse(new InputSource(new StringReader(validpayload)));
            NodeList nodeList = doc.getElementsByTagName("item");
            Channel channel = new Channel();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Item item = new Item();
                Node itemNode = nodeList.item(i);
                NodeList childNodes = itemNode.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node child = childNodes.item(j);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        switch (child.getNodeName()) {
                            case "title":
                                String title = child.getTextContent();
                                log.debug("Title: " + title);
                                item.setTitle(title);
                                break;
                            case "description":
                                String description = child.getTextContent();
                                log.debug("Description: " + description);
                                item.setDescription(description);
                                break;
                            case "pubDate":
                                String pubDate = child.getTextContent();
                                log.debug("PubDate: " + pubDate);
                                item.setPubDate(pubDate);
                                break;
                            case "link":
                                String link = child.getTextContent();
                                log.debug("Link: " + link);
                                item.setLink(link);
                                break;
                            case "media:content":
                                HashMap<String, String> content = new HashMap();
                                NamedNodeMap contentMap = child.getAttributes();
                                for (int k = 0; k < contentMap.getLength(); k++) {
                                    Node attributeNode = contentMap.item(k);
                                    String attributeName = attributeNode.getNodeName();
                                    String attributeValue = attributeNode.getNodeValue();
                                    log.debug(attributeName + "/" + attributeValue);
                                    content.put(attributeName, attributeValue);
                                }
                                item.setContent(content);
                                break;
                            case "media:title":
                                String mediaTitle = child.getTextContent();
                                item.setMediaTitle(mediaTitle);
                                log.debug("Media Title: " + mediaTitle);
                                break;
                            case "media:thumbnail":
                                String thumbNail = child.getAttributes().getNamedItem("url").getNodeValue();
                                item.setThumbNail(thumbNail);
                                log.debug("Thumb Nail: " + thumbNail);
                                break;
                        }
                    }
                }
                channel.setItem(item);
            }
            log.debug("Channel: " + channel.toString());
            return channel;
        } catch (RestClientException | ParserConfigurationException | SAXException | IOException | DOMException e) {
            log.error(e);
            throw new MediaException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e);
        }
    }
}
