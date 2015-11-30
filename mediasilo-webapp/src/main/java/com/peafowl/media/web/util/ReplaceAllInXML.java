/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.util;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author root]
 */
public class ReplaceAllInXML {

    public static void main(String[] args) {
        try {
            char letters[] = new char[26];
            int ascii = 65;
            for (int i = 0; i <= 25; i++) {
                letters[i] = (char) ascii;
                ascii += 1;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document xmlDom = docBuilder.parse("/Users/sudhgant/Desktop/dame_dash_asset_inventory.csv.xml");
            NodeList nlist = xmlDom.getElementsByTagName("Row");
            Node srcNode = nlist.item(0);

            StringWriter sw = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            serializer.transform(new DOMSource(xmlDom), new StreamResult(sw));
            String xmlString = sw.toString();
            for (int j = 1; j < nlist.getLength(); j++) {
                Node dstNode = nlist.item(j);
                for (int i = 0; i < letters.length; i++) {
                    String name = Character.toString(letters[i]);
                    replaceAttribute(xmlDom, srcNode, name, dstNode);
                    if (i >= 4) {
                        continue;
                    }
                    for (int k = 0; k < letters.length; k++) {
                        if (letters[i] == 'D' && letters[k] == 'L') {
                            break;
                        }
                        name = "" + letters[i] + letters[k];
                        replaceAttribute(xmlDom, srcNode, name, dstNode);
                    }
                }
            }
            Node parent = srcNode.getParentNode();
            parent.removeChild(srcNode);
            parent.getParentNode().removeChild(parent);
            xmlDom.normalize();
            sw = new StringWriter();
            serializer.transform(new DOMSource(xmlDom), new StreamResult(sw));
            xmlString = sw.toString();
            Files.write(Paths.get("/Users/sudhgant/Desktop/dame_dash_asset_inventory.csv_modified.xml"), xmlString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Created Successfully");
        } catch (SAXException | IOException | ParserConfigurationException | TransformerException ex) {
            System.err.println(ex);
        }
    }

    public static void replaceAttribute(Document xmlDom, Node srcNode, String attributeName, Node dstNode) {
        NamedNodeMap srcAttributes = srcNode.getAttributes();
        String attributeValue = srcAttributes.getNamedItem(attributeName).getNodeValue();
        NamedNodeMap dstAttribs = dstNode.getAttributes();
        Node dstValue = dstAttribs.getNamedItem(attributeName);
        if (dstValue != null) {
            ((Element) dstNode).removeAttribute(attributeName);
            ((Element) dstNode).setAttribute(attributeValue.contains(":") ? attributeValue.split(":")[1] : attributeValue, dstValue.getNodeValue());
        }
    }
}
