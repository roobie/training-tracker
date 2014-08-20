'use strict';

/**
 * @ngdoc function
 * @name tt.app.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the tt.app
 */
angular.module('tt.app')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
