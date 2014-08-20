(function() {
    'use strict';

    function NavigationService($location, NAVIGATION_NODES) {

        this.getActiveNode = function() {
            return NAVIGATION_NODES[$location.path()];
        };
    }

    angular.module('tt.app')
        .service('navigation', NavigationService);
}());
