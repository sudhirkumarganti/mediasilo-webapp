/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global navigationDocument, Template */

function ResourceLoader(baseurl) {
    this.BASEURL = baseurl;
}

ResourceLoader.prototype.loadResource = function (resource, callback) {
    var self = this;
    evaluateScripts([resource], function (success) {
        if (success) {
            var resource = Template.call(self);
            callback.call(self, resource);
        } else {
            var title = "Resource Loader Error";
            var description = "Error loading resource" + resource;
            alert = createAlert(title, description);
            navigationDocument.presentModal(alert);
        }
    });
};