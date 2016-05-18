(function() {
    'use strict';

    angular
        .module('fortApp')
        .controller('SecurityNavDialogController', SecurityNavDialogController);

    SecurityNavDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'SecurityNav', 'SecurityResourceEntity'];

    function SecurityNavDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, SecurityNav, SecurityResourceEntity) {
        var vm = this;
        vm.securityNav = entity;
        vm.securitynavs = SecurityNav.query();
        vm.securityresourceentities = SecurityResourceEntity.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('fortApp:securityNavUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.securityNav.id !== null) {
                SecurityNav.update(vm.securityNav, onSaveSuccess, onSaveError);
            } else {
                SecurityNav.save(vm.securityNav, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
