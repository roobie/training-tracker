(function() {
    'use strict';

    function MovementEditController(movementsService) {
        var vm = this;
        vm.item = {};

        movementsService.list().then(function(list) {
            vm.list = list;
        });

        vm.save = function() {
            movementsService.save(vm.item);
                // .then(function(response) {
                // });
        };
    }

    angular.module('tt.app')
        .directive('movementEdit', function() {
            return {
                restrict: 'E',
                templateUrl: 'views/movement_edit.html',
                controller: MovementEditController,
                controllerAs: 'movementEditC'
            };
        });
}());
