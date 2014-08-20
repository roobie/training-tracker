(function() {
    'use strict';

    angular.module('tt.app')
    .service('movementsService', function(API) {

        this.list = function() {
            return API.movements.list();
        };

        this.save = function(model) {
            if (model.id) {
                return API.movements.update(model);
            }
            return API.movements.create(model);
        };
    });
}());
