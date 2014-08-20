'use strict';

/**
 * @ngdoc function
 * @name tt.app.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the tt.app
 */
angular.module('tt.app')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
