package com.peafowl.media.web.util;

import com.peafowl.media.web.dto.MediaMessage;
import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import static com.peafowl.media.web.dto.MediaResponse.RESPONSE;
import static com.peafowl.media.web.dto.MediaResponse.RESPONSE_CODE;
import com.peafowl.media.web.exception.MediaException;
import java.util.HashMap;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Utility Class for all reusable structures across ServiceImpls
 *
 * @author sudhir
 */
public class ServiceUtil {

    protected Jaxb2Marshaller marshaller;
    protected RestTemplate template;

    public RestTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    public Jaxb2Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public MediaRequest getInitializedRequest() throws MediaException {
        MediaRequest request = new MediaRequest();
        return request;
    }

    public MediaRequest getInitializedRequest(HashMap<String, Object> attributes) throws MediaException {
        MediaRequest request = getInitializedRequest();
        request.addAllAtributes(attributes);
        return request;
    }

    public MediaResponse getInitializedResponse() throws MediaException {
        MediaResponse request = new MediaResponse();
        return request;
    }

    public MediaResponse getInitializedResponse(HashMap<String, Object> attributes) throws MediaException {
        MediaResponse response = getInitializedResponse();
        response.addAllAtributes(attributes);
        return response;
    }

    public MediaMessage populateMessage(MediaResponse response) throws MediaException {
        MediaMessage msg = new MediaMessage();
        msg.setResponseCode((Integer) response.getAttribute(RESPONSE_CODE));
        msg.setResonse(response.getAttribute(RESPONSE));
        return msg;
    }

    public String getmD5String(String input) throws MediaException {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            String md5String = new BigInteger(1, digest.digest()).toString(16);
            return md5String;
        } catch (Exception e) {
            throw new MediaException(MediaMessage.ERROR_WHILE_GET_ASSETS, e);
        }
    }

    public static void loadAllCertificates() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {

                }
            }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
}
