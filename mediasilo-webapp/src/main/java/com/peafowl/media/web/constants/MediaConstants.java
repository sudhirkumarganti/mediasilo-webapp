/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.constants;

/**
 *
 * @author root
 */
public class MediaConstants {

    public static class App {

        public static final String STORE_TYPE = "storeType";
        public static final String ACTION_TYPE = "actionType";
        public static final String ASSET_ACTION = "assetAction";
        public static final String RESPONSE_CODE = "responseCode";
        public static final String RESPONSE = "response";
        public static final String CONFIGURATION_PATH = "configPath";

    }

    public static class UserDeviceInfo {

        public static final String DEVICE_ID = "deviceId";
        public static final String ACTIVATION_CODE = "actiovationCode";
        public static final String RECORD_ID = "recordId";
        public static final String DEVICE_STATUS = "deviceStatus";
        public static final String ACTIVATION_STATUS = "activationDate";
        public static final String EXPIRY_DATE = "expiryDate";
        public static final String CREATED = "created";
        public static final String UPDATED = "updated";
        public static final String MEDIASILO_ID = "mediasiloId";
    }

    public static class TEMPLATE {

        public static final String STACK_TEMPLATE = "ST";
        public static final String PRODUCT_TEMPLATE = "PT";
        public static final String TEMPLATE_TYPE = "templateType";
        public static final String FORM_TEMPLATE = "FT";
    }

    public static class MediaSilo {

        public static final String MEDIASILO_HOST_CONTEXT = "MediaSiloHostContext";
        public static final String USER_ID = "userId";
        public static final String PASSWORD = "password";
        public static final String URL = "url";
        public static final String API_URL = "apiUrl";
        public static final String MRSS_URL = "mrssUrl";
        public static final String DEFAULT_CUSTOMER_API_URL = "defaultCustomerApiUrl";
        public static final String DEFAULT_CUSTOMER_MRSS_URL = "defaultCustomerMrssUrl";

    }

    public static enum ActionType {

        MRSS_METADATA,
        API_METADATA,
        USER_DEVICE_PAIRING_CHECK,
        DEVICE_PROVISIONING_SIGNUP,
        DEVICE_PROVISIONING_SUBMIT;
    }

    public static enum DAOType {

        USER,
        USER_DEVICE_INFO;
    }

    public static enum StoreType {

        MRSS,
        API,
        DB;
    }
}
