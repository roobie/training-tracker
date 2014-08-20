(function() {
    'use strict';


    angular.module('tt.app')
        .service('API', function($http) {
            var apiService = this;
            this.baseUrl = '//localhost:9090/v1/';

            this.movements = {
                list: function() {
                    return $http.get(apiService.baseUrl + 'movements')
                },
                create: function(model) {
                    return $http.post(
                        apiService.baseUrl + 'movements', model);
                }
            };
        });
}());
