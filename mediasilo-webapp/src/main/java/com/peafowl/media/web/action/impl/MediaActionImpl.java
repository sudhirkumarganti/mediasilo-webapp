/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peafowl.media.web.action.impl;

import com.peafowl.media.web.action.MediaActionAdapter;
import com.peafowl.media.web.constants.MediaConstants.ActionType;
import static com.peafowl.media.web.constants.MediaConstants.ActionType.API_METADATA;
import static com.peafowl.media.web.constants.MediaConstants.ActionType.MRSS_METADATA;
import static com.peafowl.media.web.constants.MediaConstants.ActionType.USER_DEVICE_PAIRING_CHECK;
import static com.peafowl.media.web.constants.MediaConstants.App.ACTION_TYPE;
import static com.peafowl.media.web.constants.MediaConstants.App.STORE_TYPE;
import static com.peafowl.media.web.constants.MediaConstants.DAOType.USER;
import static com.peafowl.media.web.constants.MediaConstants.DAOType.USER_DEVICE_INFO;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.API_URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.MEDIASILO_HOST_CONTEXT;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.MRSS_URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.PASSWORD;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.URL;
import static com.peafowl.media.web.constants.MediaConstants.MediaSilo.USER_ID;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.FORM_TEMPLATE;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.PRODUCT_TEMPLATE;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.STACK_TEMPLATE;
import static com.peafowl.media.web.constants.MediaConstants.TEMPLATE.TEMPLATE_TYPE;
import static com.peafowl.media.web.constants.MediaConstants.UserDeviceInfo.DEVICE_ID;
import static com.peafowl.media.web.constants.MediaConstants.UserDeviceInfo.MEDIASILO_ID;
import com.peafowl.media.web.constants.MediaConstants.StoreType;
import static com.peafowl.media.web.constants.MediaConstants.StoreType.DB;
import com.peafowl.media.web.dao.factory.MediaDaoFactory;
import com.peafowl.media.web.dao.impl.UserDeviceInfoDaoImpl;
import com.peafowl.media.web.dao.pojo.UserDeviceInfo;
import com.peafowl.media.web.dto.MediaRequest;
import com.peafowl.media.web.dto.MediaResponse;
import com.peafowl.media.web.exception.MediaException;
import com.peafowl.media.web.store.Store;
import com.peafowl.media.web.store.factory.StoreFactory;
import com.peafowl.media.web.store.impl.MediaDBStore;
import com.peafowl.media.web.tvml.Header;
import com.peafowl.media.web.tvml.Section;
import com.peafowl.media.web.tvml.Shelf;
import com.peafowl.media.web.tvml.lt.Button;
import com.peafowl.media.web.tvml.lt.FTDocument;
import com.peafowl.media.web.tvml.lt.Footer;
import com.peafowl.media.web.tvml.lt.FormTemplate;
import com.peafowl.media.web.tvml.lt.TextField;
import com.peafowl.media.web.tvml.pt.Badge;
import com.peafowl.media.web.tvml.pt.Banner;
import com.peafowl.media.web.tvml.pt.ButtonLockup;
import com.peafowl.media.web.tvml.pt.Description;
import com.peafowl.media.web.tvml.pt.HeroImg;
import com.peafowl.media.web.tvml.pt.Info;
import com.peafowl.media.web.tvml.pt.InfoList;
import com.peafowl.media.web.tvml.pt.PTDocument;
import com.peafowl.media.web.tvml.pt.ProductTemplate;
import com.peafowl.media.web.tvml.pt.Row;
import com.peafowl.media.web.tvml.pt.Stack;
import com.peafowl.media.web.tvml.st.Carousel;
import com.peafowl.media.web.tvml.st.Channel;
import com.peafowl.media.web.tvml.st.CollectionList;
import com.peafowl.media.web.tvml.st.Head;
import com.peafowl.media.web.tvml.st.Image;
import com.peafowl.media.web.tvml.st.Item;
import com.peafowl.media.web.tvml.st.Lockup;
import com.peafowl.media.web.tvml.st.Overlay;
import com.peafowl.media.web.tvml.st.STDocument;
import com.peafowl.media.web.tvml.st.StackTemplate;
import com.peafowl.media.web.tvml.st.Subtitle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author root
 */
public class MediaActionImpl extends MediaActionAdapter {

    @Override
    public void execute(MediaRequest request, MediaResponse response) throws MediaException {
        ActionType actionType = (ActionType) request.getAttribute(ACTION_TYPE);
        log.info("Action Type: " + actionType);
        if (actionType.toString().equals(API_METADATA.name())) {
            log.info("Executing Media Silo API Action");
            validateMandatoryParameters(request, response);
            String userId = prop.getProperty(USER_ID);
            String password = prop.getProperty(PASSWORD);
            String hostContext = prop.getProperty(MEDIASILO_HOST_CONTEXT);
            String configuredCustomerName = checkConfiguredCustomerName(API_URL, request);
            log.debug("UserId: " + userId);
            log.debug("HostContext" + hostContext);
            if ((userId == null || userId.trim().isEmpty())
                    || password == null || password.trim().isEmpty()
                    || hostContext == null || hostContext.trim().isEmpty()) {
                populateMsgAndThorwException("Invalid (Null/Empty??) MediaSilo Credentials");
            }
            populateObjectsToMediaRequest(new Object[][]{
                {PASSWORD, password},
                {USER_ID, userId},
                {MEDIASILO_HOST_CONTEXT, hostContext},
                {URL, prop.getProperty(configuredCustomerName)}}, request);
            loadTVMLTemplate(request, response);
        } else if (actionType.toString().equals(MRSS_METADATA.name())) {
            log.info("Executing MRSS Action");
            validateMandatoryParameters(request, response);
            String configuredCustomerName = checkConfiguredCustomerName(MRSS_URL, request);
            String feedsURL = prop.getProperty(configuredCustomerName);
            log.debug("URL: " + feedsURL);
            request.addAttribute(URL, feedsURL);
            loadTVMLTemplate(request, response);
        } else if (actionType.toString().equals(USER_DEVICE_PAIRING_CHECK.name())) {
            log.info("Executing User Provisioning Action");
            String deviceId = (String) request.getAttribute(DEVICE_ID);
            if (deviceId == null || deviceId.trim().isEmpty()) {
                populateMsgAndThorwException("Invalid (Null/Empty) Device Id");
            }
            MediaDBStore dbStore = (MediaDBStore) StoreFactory.getInstance(DB);
            UserDeviceInfoDaoImpl dao = (UserDeviceInfoDaoImpl) MediaDaoFactory.getInstance(USER);
            dbStore.setUserDeviceInfoDao(dao);
            dbStore.isDeviceProvisioned(deviceId);
            String status = HttpStatus.OK.toString();
            response.addAttribute(MediaResponse.RESPONSE_CODE, HttpStatus.OK);
            log.debug("Provisioing Status: " + status);
        } else if (actionType.toString().equals(ActionType.DEVICE_PROVISIONING_SIGNUP.name())) {
            log.info("Executing Device Provisioning Load Action");
            loadTVMLTemplate(request, response);
        } else if (actionType.toString().equals(ActionType.DEVICE_PROVISIONING_SUBMIT.name())) {
            log.info("Executing Device Provisioning Submit Action");
            String deviceId = (String) request.getAttribute(DEVICE_ID);
            String userId = (String) request.getAttribute(MEDIASILO_ID);
            if ((deviceId == null || deviceId.trim().isEmpty())
                    || (userId == null || userId.trim().isEmpty())) {
                populateMsgAndThorwException("Invalid (Null/Empty) DeviceId/UserId");
            }
            UserDeviceInfo info = new UserDeviceInfo();
            info.setMediaSiloId(userId);
            info.setDeviceId(deviceId);
            info.setCreated(new Date());
            MediaDBStore dbStore = (MediaDBStore) StoreFactory.getInstance(DB);
            UserDeviceInfoDaoImpl dao = (UserDeviceInfoDaoImpl) MediaDaoFactory.getInstance(USER_DEVICE_INFO);
            dbStore.setUserDeviceInfoDao(dao);
            dbStore.provisionUserToDevice(info);
            String status = HttpStatus.CREATED.toString();
            response.addAttribute(MediaResponse.RESPONSE_CODE, HttpStatus.CREATED);
            log.info("Status: " + status);
        }
    }

    private void loadTVMLTemplate(MediaRequest request, MediaResponse response) throws MediaException {
        switch ((String) request.getAttribute(TEMPLATE_TYPE)) {
            case PRODUCT_TEMPLATE: {
                StoreType type = (StoreType) request.getAttribute(STORE_TYPE);
                Store store = StoreFactory.getInstance(type);
                Channel channel = (Channel) store.loadAllAssets(request, response);
                log.info("Loading ProductTemplate");
                PTDocument tvml = productTemplate(channel);
                log.debug(tvml);
                response.addAttribute(MediaResponse.RESPONSE, tvml);
                break;
            }
            case STACK_TEMPLATE: {
                StoreType type = (StoreType) request.getAttribute(STORE_TYPE);
                Store store = StoreFactory.getInstance(type);
                Channel channel = (Channel) store.loadAllAssets(request, response);
                log.info("Loading StackTemplate");
                STDocument tvml = catalogueTemplate(channel);
                log.debug(tvml);
                response.addAttribute(MediaResponse.RESPONSE, tvml);
                break;
            }
            case FORM_TEMPLATE: {
                log.info("Loading FormTemplate");
                FTDocument tvml = formTemplate();
                log.debug(tvml);
                response.addAttribute(MediaResponse.RESPONSE, tvml);
                break;
            }
        }
    }

    private FTDocument formTemplate() {
        FTDocument document = new FTDocument();
        FormTemplate formTemplate = new FormTemplate();
        TextField userId = new TextField();
        TextField password = new TextField();
        userId.setId("userId");
        password.setId("password");
        ArrayList<TextField> formElement = new ArrayList();
        userId.setText("USER ID");
        password.setText("PASSWORD");
        formElement.add(userId);
        formElement.add(password);
        com.peafowl.media.web.tvml.lt.FTBanner banner = new com.peafowl.media.web.tvml.lt.FTBanner();
        banner.setDescription("Dame Dash Credentials");
        formTemplate.setBanner(banner);
        Footer footer = new Footer();
        Button button = new Button();
        button.setText("PROVISION DEVICE");
        footer.setButton(button);
        formTemplate.setFooter(footer);
        formTemplate.setTextField(formElement);
        ArrayList<FormTemplate> templateElement = new ArrayList();
        templateElement.add(formTemplate);
        document.setFormTemplate(templateElement);
        return document;
    }

    private PTDocument productTemplate(Channel channel) {
        PTDocument doc = new PTDocument();
        List<Lockup> shelflockupList = new ArrayList();
        Shelf shelf = new Shelf();
        ProductTemplate productTemplate = new ProductTemplate();
        List<Banner> bannerList = new ArrayList();
        int i = 0;
        for (Item item : channel.getItems()) {
            i += 1;

            //== Buy/Preview Row - 2 (Stack)
            ButtonLockup previewbutton = new ButtonLockup();
            Badge preview = new Badge();
            previewbutton.setUrl((String) item.getContent().get("url"));
            preview.setSrc("resource://button-play");
            previewbutton.setBadge(preview);
            previewbutton.setTitle("Play");
            List<ButtonLockup> stackButtonLockupList = new ArrayList();
            stackButtonLockupList.add(previewbutton);
            Row buttonLockupRow = new Row();
            buttonLockupRow.setButtonLockupList(stackButtonLockupList);

            // == Add Meta & Buy/Preview Rows
            List<Row> stackRowList = new ArrayList();
            //stackRowList.add(stacmetakrow);
            stackRowList.add(buttonLockupRow);

            // == Add Description
            Description description = new Description();
            description.setAllowsZooming(true);
            description.setMoreLabel("moreLabel");
            description.setDescription(item.getDescription());

            // == 1. Add Stack
            Stack stack = new Stack();
            stack.setTitle(item.getTitle());
            stack.setRowList(stackRowList);
            stack.setDescription(description);

            // 2. == Add Shelf
            Section shelfsection = new Section();
            Lockup shelflockup = new Lockup();
            shelflockupList.add(shelflockup);
            Image shelfimg = new Image();
            shelfimg.setId(String.valueOf(i));
            shelfimg.setHeight("325");
            shelfimg.setWidth("225");
            shelfimg.setSrc(item.getThumbNail());
            shelflockup.setImage(shelfimg);
            shelfsection.setLockup(shelflockupList);
            shelf.setSection(shelfsection);

            // == Add FTBanner
            Info bannerDirectorinfo = new Info();
            Header bannerDirectorHeader = new Header();
            bannerDirectorHeader.setTitle("Type");
            bannerDirectorinfo.setHeader(bannerDirectorHeader);
            bannerDirectorinfo.setText(Arrays.asList((String) item.getContent().get("type")));
            Info bannerHeroinfo = new Info();
            Header bannerHeroHeader = new Header();
            if (item.getContent().get("medium") != null) {
                bannerHeroHeader.setTitle("Medium");
                bannerHeroinfo.setHeader(bannerHeroHeader);
                bannerHeroinfo.setText(Arrays.asList((String) item.getContent().get("medium")));
            } else {
                bannerHeroHeader.setTitle("UploadedBy");
                bannerHeroinfo.setHeader(bannerHeroHeader);
                bannerHeroinfo.setText(Arrays.asList((String) item.getContent().get("uploadedBy")));
            }
            Info bannerDurationinfo = new Info();
            Header bannerDurationHeader = new Header();
            bannerDurationHeader.setTitle("Duration");
            bannerDurationinfo.setHeader(bannerDurationHeader);
            float duration = Float.parseFloat((String) item.getContent().get("duration"));
            int hr = (int) (duration / 3600);
            if (hr >= 24) {
                hr = (int) ((duration / 3600) / 3600);
            }
            int rem = (int) (duration % 3600);
            int mn = rem / 60;
            int sec = rem % 60;
            String hrStr = (hr < 10 ? "0" : "") + hr;
            String mnStr = (mn < 10 ? "0" : "") + mn;
            String secStr = (sec < 10 ? "0" : "") + sec;
            bannerDurationinfo.setText(Arrays.asList(hrStr + ":" + mnStr + ":" + secStr));
            Info bannferLanguageInfo = new Info();
            Header bannerLanguageHeader = new Header();
            bannerLanguageHeader.setTitle("Language");
            bannferLanguageInfo.setHeader(bannerLanguageHeader);
            bannferLanguageInfo.setText(Arrays.asList("English"));
            List<Info> bannerInfoList = new ArrayList();
            bannerInfoList.add(bannerHeroinfo);
            bannerInfoList.add(bannerDirectorinfo);
            bannerInfoList.add(bannferLanguageInfo);
            bannerInfoList.add(bannerDurationinfo);

            Banner banner = new Banner();
            HeroImg img = new HeroImg();
            img.setSrc(item.getThumbNail());
            InfoList infoList = new InfoList();
            infoList.setInfoList(bannerInfoList);
            banner.setInfoList(infoList);
            banner.setImg(img);
            banner.setStack(stack);
            banner.setId("banner_" + String.valueOf(i));

            bannerList.add(banner);

        }
        productTemplate.setShelf(shelf);
        productTemplate.setBanner(bannerList);
        doc.setTemplate(productTemplate);
        return doc;
    }

    private STDocument catalogueTemplate(Channel channel) {
        STDocument doc = new STDocument();
        /*
         FTBanner banner = new FTBanner();
         banner.setTitle("Movies");
         template.setBanner(banner);
         */
        Header header = new Header();
        header.setTitle("Movies");
        StackTemplate template = new StackTemplate();
        CollectionList collectionList = new CollectionList();
        Shelf shelf = new Shelf();
        List<Lockup> shelflockupList = new ArrayList();
        List<Lockup> carousellockupList = new ArrayList();
        Carousel carousel = new Carousel();
        for (Item item : channel.getItems()) {

            //1. For carousel
            Section carouselsection = new Section();
            Lockup carousellockup = new Lockup();
            Overlay overlay = new Overlay();
            overlay.setTitle(item.getTitle());
            Subtitle stitle = new Subtitle();
            stitle.setSubTitle(item.getDescription());
            stitle.setAllowsZooming(true);
            overlay.setSubTitle(stitle);
            carousellockup.setVideoURL((String) item.getContent().get("url"));
            carousellockupList.add(carousellockup);
            Image carouselimg = new Image();
            carouselimg.setHeight("800");
            carouselimg.setWidth("1920");
            carouselimg.setSrc(item.getThumbNail());
            carousellockup.setImage(carouselimg);
            carousellockup.setOverlay(overlay);
            carouselsection.setLockup(carousellockupList);
            carousel.setSection(carouselsection);

            //1. For Shelf
            Section shelfsection = new Section();
            Lockup shelflockup = new Lockup();
            shelflockup.setVideoURL((String) item.getContent().get("url"));
            shelflockupList.add(shelflockup);
            Image shelfimg = new Image();
            shelfimg.setHeight("260");
            shelfimg.setWidth("578");
            shelfimg.setSrc(item.getThumbNail());
            shelflockup.setImage(shelfimg);
            shelflockup.setOverlay(overlay);
            shelfsection.setLockup(shelflockupList);
            shelf.setSection(shelfsection);
        }
        shelf.setHeader(header);
        collectionList.setShelf(shelf);
        collectionList.setCarousel(carousel);
        template.setCollectionList(collectionList);
        doc.setStackTemplate(template);
        Head head = new Head();
        doc.setHead(head);
        return doc;
    }

}
