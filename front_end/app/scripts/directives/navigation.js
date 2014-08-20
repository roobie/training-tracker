(function() {
    'use strict';

    var navigationDirectiveDefinition = function() {
        return {
            restrict: 'E',
            scope: true,
            controller: NavigationController,
            controllerAs: 'NavigationC',
            templateUrl: 'views/navigation.html'
        };
    };

    function NavigationController() {
        //var vm = this;
    }

    angular.module('tt.app')
        .directive('navigation', navigationDirectiveDefinition);
}());
