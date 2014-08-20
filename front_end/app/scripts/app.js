'use strict';

/**
 * @ngdoc overview
 * @name tt.app
 * @description
 * # tt.app
 *
 * Main module of the application.
 */
angular
    .module('tt.app', [
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch'
    ])
    .constant('NAVIGATION_NODES', {
        '/': {
            templateUrl: 'views/main.html',
            controller: 'MainCtrl'
        },
        '/about': {
            templateUrl: 'views/about.html',
            controller: 'AboutCtrl'
        }
    })
    .config(function ($routeProvider, NAVIGATION_NODES) {
        Object.keys(NAVIGATION_NODES).forEach(function(node_name) {
            $routeProvider.when(node_name, NAVIGATION_NODES[node_name]);
        });

        $routeProvider.otherwise({
            redirectTo: '/'
        });
    });
