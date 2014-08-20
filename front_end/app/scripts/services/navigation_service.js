(function() {
    'use strict';

    function navigationService($location, NAVIGATION_NODES) {

        this.getActiveNode = function() {
            return NAVIGATION_NODES[$location.path()];
        };
    }

    angular.module('tt.app')
        .service('navigation', navigationService);
}());
