/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Customer - BBC

/* global navigationDocument, App, Device, Document, Storage, textField */


var domElement;
var MediaSiloApp = (function () {
    var playVideo = function (event) {
        var ele = event.target.nodeName;
        console.log('Target: ' + ele);
        if (ele === 'buttonLockup') {
            var url = event.target.getAttribute("videoURL");
            console.log('URL :' + url);
            if (url) {
                var player = new Player();
                var playlist = new Playlist();
                var mediaItem = new MediaItem("video", url);
                player.playlist = playlist;
                player.playlist.push(mediaItem);
                player.present();
            }
        } else if (ele === 'lockup') {
            var id = event.target.childNodes.item(0).getAttribute("id");
            var bannerNodes = domElement.getElementsByTagName("banner");
            console.log('ID :' + id);
            if (id) {
                var currentIdAtZeroIndex = bannerNodes.item(0).getAttribute("id").split("_")[1];
                console.log('currentIdAtZeroIndex :' + currentIdAtZeroIndex);
                if (currentIdAtZeroIndex !== id) {
                    var swapNode = domElement.getElementById("banner_" + id);
                    bannerNodes.item(0).parentNode.insertBefore(swapNode, bannerNodes.item(0));
                }
            }
        } else if (ele === 'button') {
            console.log("Submitting the credentials");
            var deviceId = Device.vendorIdentifier;
            var Keyboard = domElement.getElementById("userId").getFeature('Keyboard');
            var mediaSiloId = Keyboard.text;
            console.log("Id: "+ mediaSiloId);
            provisionDeviceToUser(deviceId, mediaSiloId, "appletv$123");
        }
    };
    var checkUserIdInStorage = function () {
        var userId = localStorage.getItem('userId');
        if (userId === undefined) {
            return false;
        } else {
            console.log('UserId: ' + userId);
            return false;
        }
    };

    var Template = function () {
        var isDeviceProvisioned = checkUserIdInStorage();
        if (isDeviceProvisioned) {
            console.log('App is Provisioned. Loding Carousal');
            return loadContentTemplate();
        } else {
            console.log("App is not provisioned");
            loadSignupTemplate();
        }
    };

    var provisionDeviceToUser = function (deviceId, mediaSiloId, password) {
        var provisionRequest = new XMLHttpRequest();
        provisionRequest.onreadystatechange = function () {
            if (provisionRequest.readyState === 4 && provisionRequest.status === 200) {
                console.log("Provisioned Successfully");
                return loadContentTemplate(deviceId, mediaSiloId);
            }
        };
        provisionRequest.open("POST", "http://localhost:8080/mediasiloapp/api/device/provision", true);
        provisionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        provisionRequest.send('deviceId=' + deviceId + '&mediaSiloId=' + mediaSiloId + '&password=' + password);
    };

    var loadContentTemplate = function (deviceId, mediaSiloId) {
        console.log('Saving Provisioned Data locally');
        localStorage.setItem("deviceId", deviceId);
        localStorage.setItem("mediaSiloId", mediaSiloId);
        var templateXHR = new XMLHttpRequest();
        templateXHR.responseType = "document";
        templateXHR.addEventListener("load", function () {
            domElement = templateXHR.responseXML;
            domElement.addEventListener("select", playVideo.bind(this), false);
            navigationDocument.pushDocument(domElement);
        }, false);
        templateXHR.open("GET", "http://localhost:8080/mediasiloapp/api/metadata/pt/loadAssets", true);
        templateXHR.send();
        return templateXHR;
    };

    var createAlert = function (title, description) {
        var alertString = '<?xml version="1.0" encoding="UTF-8" ?>'
                + '<document>'
                + '<alertTemplate>'
                + '<title>' + title + '</title>'
                + '<description>' + description + '</description>'
                + '</alertTemplate>'
                + '</document>';
        var parser = new DOMParser();
        var alertDoc = parser.parseFromString(alertString, "application/xml");
        return alertDoc;
    };
    var loadSignupTemplate = function () {
        var templateXHR = new XMLHttpRequest();
        templateXHR.responseType = "document";
        templateXHR.addEventListener("load", function () {
            domElement = templateXHR.responseXML;
            domElement.addEventListener("select", playVideo.bind(this), false);
            navigationDocument.pushDocument(domElement);
        }, false);
        templateXHR.open("GET", "http://localhost:8080/mediasiloapp/api/device/signup", true);
        templateXHR.send();
        return templateXHR;
    };

    return {
        Template: Template
    };
})();

App.onLaunch = function (options) {
    MediaSiloApp.Template();
};
