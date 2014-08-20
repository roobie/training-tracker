'use strict';

/**
 * @ngdoc overview
 * @name trainingTrackerApp
 * @description
 * # trainingTrackerApp
 *
 * Main module of the application.
 */
angular
  .module('trainingTrackerApp', [
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
