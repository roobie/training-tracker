(function() {
    'use strict';

    angular.module('tt.app')
        .service('notificationsService', function() {
            this.info = function() {
                console.log.apply(console, arguments);
            };
        });
}());
