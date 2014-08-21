(function() {
    'use strict';


    angular.module('tt.app')
        .service('API', function($http) {
            var apiService = this;
            this.baseUrl = '//localhost:3000/v1/';

            var extractData = function(r) {
                return r.data;
            };

            this.movements = {
                list: function() {
                    return $http.get(apiService.baseUrl + 'movements').then(extractData);
                },
                create: function(model) {
                    return $http.post(
                        apiService.baseUrl + 'movements', model).then(extractData);
                }
            };
        });
}());
