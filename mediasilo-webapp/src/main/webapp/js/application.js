/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Customer - BBC

/* global navigationDocument, App */


var domElement;
var MediaSiloApp = (function () {
    var playVideo = function (event) {
        var ele = event.target.nodeName;
        console.log('Target: ' + ele);
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
    };
    

    var Template = function () {
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

    return {
        Template: Template
    };
})();

App.onLaunch = function (options) {
    MediaSiloApp.Template();
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
